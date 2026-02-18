package com.grocery.backend.basket;

import com.grocery.backend.price.Price;
import com.grocery.backend.price.PriceRepository;
import com.grocery.backend.store.Store;
import com.grocery.backend.store.StoreRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class BasketService {

    private final PriceRepository priceRepo;
    private final StoreRepository storeRepo;

    public BasketService(PriceRepository priceRepo, StoreRepository storeRepo) {
        this.priceRepo = priceRepo;
        this.storeRepo = storeRepo;
    }

    public BasketCompareResponse compare(BasketCompareRequest request) {
        if (request == null || request.getItems() == null || request.getItems().isEmpty()) {
            return new BasketCompareResponse(
                    null,
                    List.of(),
                    null,
                    null,
                    List.of(),
                    null
            );
        }

        // 1) Aggregate quantities by productId (sum if the same product appears multiple times)
        Map<Long, Integer> qtyByProductId = new HashMap<>();
        for (BasketCompareRequest.Item item : request.getItems()) {
            if (item == null) continue;
            if (item.getProductId() == null) continue;
            if (item.getQuantity() <= 0) continue;
            qtyByProductId.merge(item.getProductId(), item.getQuantity(), Integer::sum);
        }

        if (qtyByProductId.isEmpty()) {
            return new BasketCompareResponse(
                    null,
                    List.of(),
                    null,
                    null,
                    List.of(),
                    null
            );
        }

        List<Long> productIds = new ArrayList<>(qtyByProductId.keySet());

        // 2) Get the latest available date
        LocalDate latestDate = priceRepo.findLatestDate();
        if (latestDate == null) {
            throw new IllegalStateException("No prices in database yet.");
        }

        // Get all prices for this date for the required products
        List<Price> prices = priceRepo.findByDateAndProduct_IdIn(latestDate, productIds);

        // 4) Build a map storeId -> (productId -> unitPrice)
        Map<Long, Map<Long, BigDecimal>> storeToProductPrice = new HashMap<>();
        // + map productId -> productName (taken from Price -> Product)
        Map<Long, String> productNames = new HashMap<>();

        for (Price p : prices) {
            Long storeId = p.getStore().getId();
            Long productId = p.getProduct().getId();

            storeToProductPrice
                    .computeIfAbsent(storeId, k -> new HashMap<>())
                    .put(productId, p.getPrice());

            // store the name only once
            productNames.putIfAbsent(productId, p.getProduct().getName());
        }

        // 5) For each store, calculate total + detailed line items
        List<Store> stores = storeRepo.findAll();
        List<BasketCompareResponse.StoreTotal> totals = new ArrayList<>();
        List<BasketCompareResponse.StoreBreakdown> breakdowns = new ArrayList<>();

        for (Store store : stores) {
            Map<Long, BigDecimal> productPrice = storeToProductPrice.get(store.getId());
            if (productPrice == null) continue;

            // the store is valid only if it has prices for ALL products in the basket
            boolean hasAll = productIds.stream().allMatch(productPrice::containsKey);
            if (!hasAll) continue;

            BigDecimal storeTotal = BigDecimal.ZERO;
            List<BasketCompareResponse.LineItem> items = new ArrayList<>();

            for (Long pid : productIds) {
                int qty = qtyByProductId.getOrDefault(pid, 0);
                BigDecimal unitPrice = productPrice.get(pid);
                BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(qty));
                storeTotal = storeTotal.add(lineTotal);

                String productName = productNames.getOrDefault(pid, "Product " + pid);

                items.add(new BasketCompareResponse.LineItem(
                        pid,
                        productName,
                        qty,
                        unitPrice,
                        lineTotal
                ));
            }

            totals.add(new BasketCompareResponse.StoreTotal(store.getId(), store.getName(), storeTotal));
            breakdowns.add(new BasketCompareResponse.StoreBreakdown(store.getId(), store.getName(), storeTotal, items));
        }

        // 6) determine the cheapest store (first after sorting)
        totals.sort(Comparator.comparing(BasketCompareResponse.StoreTotal::getTotal));
        breakdowns.sort(Comparator.comparing(BasketCompareResponse.StoreBreakdown::getStoreTotal));

        Long bestStoreId = totals.isEmpty() ? null : totals.get(0).getStoreId();
        BigDecimal bestTotal = totals.isEmpty() ? null : totals.get(0).getTotal();
        BasketCompareResponse.StoreBreakdown cheapestStore = breakdowns.isEmpty() ? null : breakdowns.get(0);

        return new BasketCompareResponse(
                latestDate,
                totals,
                bestStoreId,
                bestTotal,
                breakdowns,
                cheapestStore
        );
    }
}

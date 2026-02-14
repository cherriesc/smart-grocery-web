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
        if (request.getItems() == null || request.getItems().isEmpty()) {
            return new BasketCompareResponse(null, List.of(), null, null);
        }

        LocalDate latestDate = priceRepo.findLatestDate();
        if (latestDate == null) {
            throw new IllegalStateException("No prices in database yet.");
        }

        Map<Long, Integer> qtyByProductId = new HashMap<>();
        for (BasketCompareRequest.Item item : request.getItems()) {
            if (item.getProductId() == null || item.getQuantity() <= 0) continue;
            qtyByProductId.merge(item.getProductId(), item.getQuantity(), Integer::sum);
        }
        if (qtyByProductId.isEmpty()) {
            return new BasketCompareResponse(latestDate, List.of(), null, null);
        }

        List<Long> productIds = new ArrayList<>(qtyByProductId.keySet());

        List<Price> prices = priceRepo.findByDateAndProduct_IdIn(latestDate, productIds);

        Map<Long, Map<Long, BigDecimal>> storeToProductPrice = new HashMap<>();
        for (Price p : prices) {
            Long storeId = p.getStore().getId();
            Long productId = p.getProduct().getId();
            storeToProductPrice
                    .computeIfAbsent(storeId, k -> new HashMap<>())
                    .put(productId, p.getPrice());
        }

        List<Store> stores = storeRepo.findAll();
        List<BasketCompareResponse.StoreTotal> results = new ArrayList<>();

        for (Store s : stores) {
            Map<Long, BigDecimal> productPrice = storeToProductPrice.get(s.getId());
            if (productPrice == null) continue;

            boolean hasAll = productIds.stream().allMatch(productPrice::containsKey);
            if (!hasAll) continue;

            BigDecimal total = BigDecimal.ZERO;
            for (Long pid : productIds) {
                BigDecimal unitPrice = productPrice.get(pid);
                int qty = qtyByProductId.get(pid);
                total = total.add(unitPrice.multiply(BigDecimal.valueOf(qty)));
            }

            results.add(new BasketCompareResponse.StoreTotal(s.getId(), s.getName(), total));
        }

        results.sort(Comparator.comparing(BasketCompareResponse.StoreTotal::getTotal));

        Long bestStoreId = results.isEmpty() ? null : results.get(0).getStoreId();
        BigDecimal bestTotal = results.isEmpty() ? null : results.get(0).getTotal();

        return new BasketCompareResponse(latestDate, results, bestStoreId, bestTotal);
    }
}

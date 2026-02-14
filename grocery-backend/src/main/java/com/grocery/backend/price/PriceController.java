package com.grocery.backend.price;

import com.grocery.backend.product.Product;
import com.grocery.backend.product.ProductRepository;
import com.grocery.backend.store.Store;
import com.grocery.backend.store.StoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceRepository priceRepo;
    private final ProductRepository productRepo;
    private final StoreRepository storeRepo;

    public PriceController(PriceRepository priceRepo, ProductRepository productRepo, StoreRepository storeRepo) {
        this.priceRepo = priceRepo;
        this.productRepo = productRepo;
        this.storeRepo = storeRepo;
    }

    @GetMapping
    public List<Price> getAll() {
        return priceRepo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Price create(@RequestBody CreatePriceRequest request) {

        Product product = productRepo.findById(request.getProductId()).orElseThrow();

        Store store = storeRepo.findById(request.getStoreId()).orElseThrow();

        Price price = new Price(product, store, request.getPrice(), request.getDate()
        );

        return priceRepo.save(price);
    }
}

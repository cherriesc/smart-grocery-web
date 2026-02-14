package com.grocery.backend.store;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreRepository repo;

    public StoreController(StoreRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Store> getAll() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Store create(@RequestBody Store store) {
        return repo.save(store);
    }
}

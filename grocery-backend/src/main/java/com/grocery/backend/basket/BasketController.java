package com.grocery.backend.basket;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService service;

    public BasketController(BasketService service) {
        this.service = service;
    }

    @PostMapping("/compare")
    public BasketCompareResponse compare(@RequestBody BasketCompareRequest request) {
        return service.compare(request);
    }
}

package com.grocery.backend.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    // Categories (without separate controller file)
    // URL: GET /products/categories
    @GetMapping("/categories")
    public List<String> categories() {
        return List.of(
                "Dairy",
                "Bakery",
                "Meat & Fish",
                "Fruits & Vegetables",
                "Grains & Pasta",
                "Drinks",
                "Snacks",
                "Household"
        );
    }

    // Products: all filters by categories
    // URL: GET /products
    // URL: GET /products?category=Dairy
    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String category) {
        if (category == null || category.isBlank()) {
            return repo.findAll();
        }
        return repo.findByCategoryIgnoreCase(category.trim());
    }
}


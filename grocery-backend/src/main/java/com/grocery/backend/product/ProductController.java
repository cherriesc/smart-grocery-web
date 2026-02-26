package com.grocery.backend.product;

import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    // Categories (for the navbar)
    // GET /products/categories
    @GetMapping("/categories")
    public List<String> categories() {
        // Если хочешь строго фиксированный порядок как в дизайне — верни List.of(...)
        return List.of(
                "Fresh Food",
                "Bakery",
                "Dairy & Eggs",
                "Meat & Seafood",
                "Frozen Food",
                "Pantry Items",
                "Snacks & Beverages"
        );
    }

    // Subcategories by category
    // GET /products/subcategories?category=Fresh%20Food
    @GetMapping("/subcategories")
    public List<String> subcategories(@RequestParam String category) {
        // Можно либо брать из БД:
        // return repo.findDistinctSubcategoriesByCategory(category);

        // Либо фиксировано как в дизайне (стабильно и без зависимости от сидов):
        return switch (category) {
            case "Fresh Food" -> List.of("Fruits", "Vegetables");
            case "Bakery" -> List.of("Bread", "Pastries", "Cakes");
            case "Dairy & Eggs" -> List.of("Milk", "Cheese", "Yoghurt", "Butter");
            case "Meat & Seafood" -> List.of("Beef", "Pork", "Chicken", "Turkey", "Fish", "Cold Cuts");
            case "Frozen Food" -> List.of("Frozen Meals", "Finger Foods", "Vegetables", "Ice Cream");
            case "Pantry Items" -> List.of("Grains & Pasta", "Baking supplies", "Canned Food", "Sauces & Condiments", "Spices & Seasonings");
            case "Snacks & Beverages" -> List.of("Sweet snacks", "Salty snacks", "Hot drinks", "Cold drinks", "Water & Functional Drinks");
            default -> List.of();
        };
    }

    // Optional: full menu map for frontend in one call
    // GET /products/menu
    @GetMapping("/menu")
    public Map<String, List<String>> menu() {
        Map<String, List<String>> m = new LinkedHashMap<>();
        for (String cat : categories()) {
            m.put(cat, subcategories(cat));
        }
        return m;
    }

    // Products with filters
    // GET /products
    // GET /products?category=Fresh%20Food
    // GET /products?category=Fresh%20Food&subcategory=Fruits
    @GetMapping
    public List<Product> getAll(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String subcategory
    ) {
        if (category == null || category.isBlank()) {
            return repo.findAll();
        }
        if (subcategory == null || subcategory.isBlank()) {
            return repo.findByCategoryIgnoreCase(category.trim());
        }
        return repo.findByCategoryIgnoreCaseAndSubcategoryIgnoreCase(category.trim(), subcategory.trim());
    }
}
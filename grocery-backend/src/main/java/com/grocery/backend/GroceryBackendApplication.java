package com.grocery.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroceryBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroceryBackendApplication.class, args);
    }

}

//http://localhost:8080/products
//http://localhost:8080/stores
//http://localhost:8080/prices
//http://localhost:8080/products/categories all categories
//http://localhost:8080/products/subcategories?category=Bakery all subcategories from category - Bakery
//http://localhost:8080/products?category=Bakery all products from bakery
//http://localhost:8080/products?category=Bakery&subcategory=Pastries all products only from subcategory - Pastries
//
//
//

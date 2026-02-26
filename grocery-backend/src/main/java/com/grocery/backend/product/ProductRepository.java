package com.grocery.backend.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByCategoryIgnoreCaseAndSubcategoryIgnoreCase(String category, String subcategory);

    @Query("select distinct p.category from Product p order by p.category")
    List<String> findDistinctCategories();

    @Query("select distinct p.subcategory from Product p where lower(p.category) = lower(?1) order by p.subcategory")
    List<String> findDistinctSubcategoriesByCategory(String category);
}
package com.grocery.backend.recipe;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grocery.backend.product.Product;
import com.grocery.backend.product.ProductRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

import static com.grocery.backend.recipe.RecipeModels.*;

@Service
public class RecipeService {

    private final ProductRepository productRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    public RecipeService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    private List<RecipeJson> loadRecipes() {
        try {
            ClassPathResource res = new ClassPathResource("recipes.json");
            try (InputStream in = res.getInputStream()) {
                return mapper.readValue(in, new TypeReference<List<RecipeJson>>() {});
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load recipes.json", e);
        }
    }

    public List<RecipeSummary> getAll() {
        return loadRecipes().stream()
                .map(r -> new RecipeSummary(r.id(), r.title(), r.tag(), r.imageUrl(), r.shortDescription()))
                .toList();
    }

    public RecipeDetails getOne(long id) {
        RecipeJson r = loadRecipes().stream()
                .filter(x -> x.id() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found: " + id));

        List<RecipeIngredient> resolved = r.ingredients().stream().map(i -> {
            Product p = productRepo.findFirstByNameIgnoreCase(i.productName())
                    .orElseThrow(() -> new IllegalStateException("Product not found in DB: " + i.productName()));
            return new RecipeIngredient(p.getId(), p.getName(), i.quantity());
        }).toList();

        return new RecipeDetails(
                r.id(), r.title(), r.tag(), r.imageUrl(), r.shortDescription(),
                r.prepMinutes(), r.cookMinutes(), r.difficulty(),
                r.steps(), resolved
        );
    }
}
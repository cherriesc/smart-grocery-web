package com.grocery.backend.recipe;

import java.util.List;

public class RecipeModels {

    // Stored in recipes.json
    public record RecipeJson(
            Long id,
            String title,
            String tag,
            String imageUrl,
            String shortDescription,
            int prepMinutes,
            int cookMinutes,
            String difficulty,
            List<String> steps,
            List<IngredientByName> ingredients
    ) {}

    public record IngredientByName(String productName, int quantity) {}

    // Returned to frontend (ready for basket)
    public record RecipeSummary(Long id, String title, String tag, String imageUrl, String shortDescription) {}

    public record RecipeDetails(
            Long id,
            String title,
            String tag,
            String imageUrl,
            String shortDescription,
            int prepMinutes,
            int cookMinutes,
            String difficulty,
            List<String> steps,
            List<RecipeIngredient> ingredients
    ) {}

    public record RecipeIngredient(Long productId, String productName, int quantity) {}
}
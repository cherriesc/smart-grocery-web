package com.grocery.backend.recipe;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.grocery.backend.recipe.RecipeModels.*;

@RestController
@RequestMapping("/recipes")
@CrossOrigin(origins = "*")
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    // List page
    @GetMapping
    public List<RecipeSummary> all() {
        return service.getAll();
    }

    // Details page
    @GetMapping("/{id}")
    public RecipeDetails one(@PathVariable long id) {
        return service.getOne(id);
    }
}
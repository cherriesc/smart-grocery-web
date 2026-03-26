package com.grocery.backend.ai;

import com.grocery.backend.basket.BasketCompareRequest;
import com.grocery.backend.product.Product;
import com.grocery.backend.product.ProductRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final OpenAIService openAIService;
    private final ProductRepository productRepository;

    public AiController(OpenAIService openAIService, ProductRepository productRepository) {
        this.openAIService = openAIService;
        this.productRepository = productRepository;
    }

    // 1) Простой тестовый endpoint: ты вручную передаешь строку продуктов
    @PostMapping(value = "/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> recipes(@RequestBody String productsString) {
        // productsString приходит как JSON-string, например "milk, bread, eggs"
        String cleaned = (productsString == null) ? "" : productsString.trim();
        if (cleaned.isBlank()) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Empty products string\"}");
        }

        String json = openAIService.generateRecipes(cleaned);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }

    // 2) Основной endpoint: берём товары из "корзины" (BasketCompareRequest.items)
    // Важно: он НЕ требует логина. Фронт просто отправляет текущее содержимое корзины.
    @PostMapping(value = "/recipes/from-basket", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> recipesFromBasket(@RequestBody BasketCompareRequest request) {

        if (request == null || request.getItems() == null || request.getItems().isEmpty()) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Basket is empty\"}");
        }

        // Берем productId из items (убираем null и дубликаты)
        List<Long> productIds = request.getItems().stream()
                .filter(Objects::nonNull)
                .map(BasketCompareRequest.Item::getProductId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        if (productIds.isEmpty()) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"No valid productIds in basket\"}");
        }

        // Подтягиваем имена продуктов из БД
        List<Product> products = productRepository.findAllById(productIds);

        if (products.isEmpty()) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Products not found in DB for given ids\"}");
        }

        // Сформируем строку для AI: "milk, bread, eggs"
        String productsString = products.stream()
                .map(Product::getName)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .distinct()
                .collect(Collectors.joining(", "));

        if (productsString.isBlank()) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Product names are empty\"}");
        }

        String json = openAIService.generateRecipes(productsString);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }
}

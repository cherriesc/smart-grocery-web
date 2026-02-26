package com.grocery.backend.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Basic user info (for profile page)
    @GetMapping("/{userId}")
    public UserInfo getUser(@PathVariable Long userId) {
        User u = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new UserInfo(u.getId(), u.getUsername(), u.getEmail());
    }

    // Get basket as list of items
    @GetMapping("/{userId}/basket")
    public List<BasketItem> getBasket(@PathVariable Long userId) throws Exception {
        User u = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        String json = u.getBasketJson();
        if (json == null || json.isBlank()) json = "[]";
        return mapper.readValue(json, new TypeReference<List<BasketItem>>() {});
    }

    // Save basket (front-end sends productId + quantity)
    @PutMapping("/{userId}/basket")
    public void saveBasket(@PathVariable Long userId, @RequestBody BasketSaveRequest req) throws Exception {
        User u = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<BasketItem> items = (req == null || req.items() == null) ? List.of() : req.items();
        String json = mapper.writeValueAsString(items);
        u.setBasketJson(json);
        userRepo.save(u);
    }

    // --- DTOs ---
    public record UserInfo(Long id, String username, String email) {}
    public record BasketItem(Long productId, int quantity) {}
    public record BasketSaveRequest(List<BasketItem> items) {}
}
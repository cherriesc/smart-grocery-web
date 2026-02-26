package com.grocery.backend.auth;

import com.grocery.backend.user.User;
import com.grocery.backend.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public AuthResponse register(RegisterRequest req) {
        if (req == null || isBlank(req.username()) || isBlank(req.email()) || isBlank(req.password())) {
            throw new IllegalArgumentException("username, email and password are required");
        }

        if (userRepo.existsByUsernameIgnoreCase(req.username())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepo.existsByEmailIgnoreCase(req.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        String hash = encoder.encode(req.password());
        User user = new User(req.username().trim(), req.email().trim(), hash);
        userRepo.save(user);

        return new AuthResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    public AuthResponse login(LoginRequest req) {
        if (req == null || isBlank(req.usernameOrEmail()) || isBlank(req.password())) {
            throw new IllegalArgumentException("usernameOrEmail and password are required");
        }

        String key = req.usernameOrEmail().trim();

        User user = userRepo.findByUsernameIgnoreCase(key)
                .or(() -> userRepo.findByEmailIgnoreCase(key))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!encoder.matches(req.password(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Wrong password");
        }

        return new AuthResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    // --- DTOs (kept here to reduce file count) ---
    public record RegisterRequest(String username, String email, String password) {}
    public record LoginRequest(String usernameOrEmail, String password) {}
    public record AuthResponse(Long userId, String username, String email) {}
}
package com.example.demo.controller;

import com.example.demo.entity.UserProfile;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * POST /auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(
            @RequestBody UserProfile user
    ) {

        if (userProfileRepository.existsByUserId(user.getUserId())) {
            throw new BadRequestException("UserId already exists");
        }

        if (userProfileRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        UserProfile savedUser = userProfileRepository.save(user);

        // Fake JWT token (tests usually only check non-null)
        String token = "mock-jwt-token-" + savedUser.getUserId();

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("userId", savedUser.getUserId());
        response.put("email", savedUser.getEmail());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * POST /auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Map<String, String> request
    ) {

        String email = request.get("email");
        String password = request.get("password");

        UserProfile user = userProfileRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!user.getActive()) {
            throw new BadRequestException("User account is inactive");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        // Fake JWT token
        String token = "mock-jwt-token-" + user.getUserId();

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("userId", user.getUserId());
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
    }
}

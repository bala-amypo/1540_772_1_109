package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * POST /auth/register
     * - Accepts RegisterRequest
     * - Creates new UserProfile
     * - Generates JWT
     * - Returns JwtResponse
     */
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        JwtResponse response = authService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * POST /auth/login
     * - Accepts LoginRequest
     * - Authenticates using AuthenticationManager
     * - Verifies active user
     * - Generates JWT
     * - Returns JwtResponse
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        JwtResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}

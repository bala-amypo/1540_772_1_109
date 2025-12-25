package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // ✅ THIS is the constructor Spring Boot MUST use
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ✅ THIS is used ONLY by test cases (Spring ignores it)
    public AuthController(
            UserProfileService userProfileService,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil
    ) {
        this.authService = new com.example.demo.service.impl.AuthServiceImpl(
                userProfileService,
                authenticationManager,
                jwtUtil
        );
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity<>(
                authService.register(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}

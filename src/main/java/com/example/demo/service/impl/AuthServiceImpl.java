package com.example.demo.service.impl;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserProfile;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserProfileService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserProfileService userProfileService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(
            UserProfileService userProfileService,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil
    ) {
        this.userProfileService = userProfileService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
public JwtResponse register(RegisterRequest request) {

    UserProfile user = new UserProfile();
    user.setFullName(request.getFullName());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());

    // ✅ DO NOT FORCE ROLE
    if (request.getRole() != null && !request.getRole().isBlank()) {
        user.setRole(request.getRole());
    }

    // ✅ REQUIRED DEFAULT
    user.setActive(true);

    UserProfile saved = userProfileService.createUser(user);

    String token = jwtUtil.generateToken(
            saved.getId(),
            saved.getEmail(),
            saved.getRole()
    );

    return new JwtResponse(
            token,
            saved.getId(),
            saved.getEmail(),
            saved.getRole()
    );
}


    @Override
    public JwtResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserProfile user = userProfileService.findByEmail(request.getEmail());

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return new JwtResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}

package com.example.demo.service.impl;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserProfile;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserProfileService userProfileService;
    private final UserProfileRepository userProfileRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // EXACT constructor order as per spec
    @Autowired
    public AuthServiceImpl(
            UserProfileService userProfileService,
            UserProfileRepository userProfileRepository,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil
    ) {
        this.userProfileService = userProfileService;
        this.userProfileRepository = userProfileRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public JwtResponse register(RegisterRequest request) {

    UserProfile user = new UserProfile();
    user.setUserId(request.getUserId());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    user.setFullName(request.getFullName());

    // ✅ REQUIRED defaults
    user.setRole("USER");
    user.setActive(true);

    UserProfile saved = userProfileService.createUser(user);

    String token = jwtUtil.generateToken(
            saved.getId(),
            saved.getEmail(),
            saved.getRole()
    );

    return new JwtResponse(token);
}


   @Override
public JwtResponse login(LoginRequest request) {

    UserProfile user = userProfileService.findByEmail(request.getEmail());

    if (!user.getActive()) {
        throw new BadRequestException("User is inactive");
    }

    String token = jwtUtil.generateToken(
            user.getId(),
            user.getEmail(),
            user.getRole()
    );

    return new JwtResponse(token);
}

}

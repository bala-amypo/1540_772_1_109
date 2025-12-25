package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileServiceImpl(
            UserProfileRepository userProfileRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserProfile register(UserProfile user) {
        // ✅ DEFAULT ROLE REQUIRED BY TEST
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userProfileRepository.save(user);
    }

    @Override
    public UserProfile getById(Long id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }
}

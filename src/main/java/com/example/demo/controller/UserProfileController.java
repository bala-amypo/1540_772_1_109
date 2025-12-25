package com.example.demo.controller;

import com.example.demo.entity.UserProfile;
import com.example.demo.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private UserProfileService userProfileService;

    // ✅ REQUIRED BY TESTS
    public UserProfileController() {}

    // ✅ REQUIRED BY TESTS
    public UserProfileController(UserProfileService service) {
        this.userProfileService = service;
    }

    @Autowired
    public void setUserProfileService(UserProfileService service) {
        this.userProfileService = service;
    }

    @PostMapping
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfile profile) {
        return new ResponseEntity<>(
                userProfileService.createUser(profile),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userProfileService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        return ResponseEntity.ok(userProfileService.getAllUsers());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<UserProfile> updateUserStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        return ResponseEntity.ok(
                userProfileService.updateUserStatus(id, active)
        );
    }

    @GetMapping("/lookup/{userId}")
    public ResponseEntity<UserProfile> findByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(userProfileService.findByUserId(userId));
    }
}

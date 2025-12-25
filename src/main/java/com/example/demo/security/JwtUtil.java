package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private final byte[] secret;
    private final Long expirationMs;

    // REQUIRED constructor (tests use this directly)
    public JwtUtil(byte[] secret, Long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }

    // Generate token with required fields
    public String generateToken(Long userId, String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Extract email (subject)
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Extract role
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // Extract userId
    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // Internal helper
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}

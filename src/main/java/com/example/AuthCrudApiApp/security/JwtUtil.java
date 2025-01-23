package com.example.AuthCrudApiApp.security;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtUtil {

    // Secret key for signing the token (must be at least 32 characters)
    @Value("${jwt.secret}")
	private String secretKey;

    
    // Token expiration time (10 minutes)
    private long expirationTime = 10 * 60 * 1000;

    // Generate JWT token with username and expiration time
    public String generateToken(String username) {
        System.out.println("Generating TOKEN!!");
        
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Get claims (payload data) from the token
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validate the token (check if it's expired or altered)
    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return !claims.getExpiration().before(new Date()); // Token not expired
        } catch (Exception e) {
            return false; // Invalid token
        }
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }
}

package com.example.AuthCrudApiApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JWT Filter for intercepting HTTP requests and validating JWT tokens.
 */
@Component
@WebFilter("/*") // Apply the filter to all endpoints
public class JwtFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Intercepts each request, validates the JWT token, and sets the security context.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Cast to HttpServletRequest to access HTTP-specific methods
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Retrieve the Authorization header and requested path
        String authorizationHeader = httpRequest.getHeader("Authorization");
        String path = httpRequest.getServletPath();

        // Allow public endpoints (login and register) to pass through without validation
        if ("/login".equals(path) || "/register".equals(path)) {
            chain.doFilter(request, response);
            return;
        }

        // Validate the JWT token if the Authorization header exists and starts with "Bearer"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Extract the token
            if (jwtUtil.validateToken(token)) { // Validate the token
                String username = jwtUtil.extractUsername(token);

                // Set the authenticated user in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(username, null, null)
                );
            }
        }

        // Pass the request to the next filter or the target controller
        chain.doFilter(request, response);
    }
}

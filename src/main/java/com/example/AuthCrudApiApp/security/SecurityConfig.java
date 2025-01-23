package com.example.AuthCrudApiApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // Password encoder bean using BCrypt
    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configure security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("In securityFilterChain method");

        http.csrf().disable() // Disable CSRF protection as we're using JWT
            .authorizeHttpRequests(auth -> auth
                // Publicly accessible endpoints for registration and login
                .requestMatchers("/register/**", "/login/**").permitAll()
                // All other endpoints require authentication
                .requestMatchers("/**").authenticated()
                // Deny access to any other requests
                .anyRequest().denyAll()
            )
            // Add JWT filter before the UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            // Handle 403 (Forbidden) errors
            .exceptionHandling()
            .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
        
        return http.build();
    }

   
}

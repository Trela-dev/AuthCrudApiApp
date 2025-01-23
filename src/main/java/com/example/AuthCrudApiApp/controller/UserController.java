package com.example.AuthCrudApiApp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AuthCrudApiApp.entity.User;
import com.example.AuthCrudApiApp.security.JwtUtil;
import com.example.AuthCrudApiApp.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("") // Base path
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // Endpoint for registering
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        Optional<User> existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(409).body("The username is already taken, please choose a different one.");
        } else {
            userService.registerUser(user.getUsername(), user.getPassword());
            return ResponseEntity.ok("User registered successfully!");
        }
    }

    // Endpoint for logging in
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (isAuthenticated) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // Endpoint for getting all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Endpoint for deleting a user by ID
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted) {
            return ResponseEntity.ok("User with ID " + id + " has been successfully deleted.");
        } else {
            return ResponseEntity.status(404).body("No user found with ID " + id + ".");
        }
    }

    // Endpoint for updating a user by ID
    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUserProfile(@PathVariable long id, @RequestBody User updatedUser) {
        boolean userExists = userService.doesUserExist(id);
        if (userExists) {
            userService.updateUser(id, updatedUser);
            return ResponseEntity.ok("User with ID " + id + " has been successfully updated!");
        } else {
            return ResponseEntity.status(404).body("No user found with ID " + id + ".");
        }
    }
}

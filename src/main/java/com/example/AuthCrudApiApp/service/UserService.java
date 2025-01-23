package com.example.AuthCrudApiApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.AuthCrudApiApp.entity.User;
import com.example.AuthCrudApiApp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor to initialize UserRepository and PasswordEncoder
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user with encrypted password
    public void registerUser(String username, String password) {
        // Encrypt the password
        String encryptedPassword = passwordEncoder.encode(password);

        // Create new user and save to repository
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(encryptedPassword);
        
        userRepository.save(newUser);
    }

    // Get all users from the repository
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update an existing user's information
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            // Encrypt the new password
            String encryptedPassword = passwordEncoder.encode(updatedUser.getPassword());

            // Update user details
            user.setUsername(updatedUser.getUsername());
            user.setPassword(encryptedPassword);
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // Authenticate user by checking credentials
    public boolean authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    // Delete a user by their ID
    public boolean deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Check if a user exists by their ID
    public boolean doesUserExist(Long id) {
        return userRepository.existsById(id);
    }

    // Get a user by their username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

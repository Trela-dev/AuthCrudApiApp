package com.example.AuthCrudApiApp.entity;

// Import statements for JPA annotations
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Represents a User entity mapped to the database table.
 */
@Entity
@Table(name = "\"user\"") // Mapping the entity to the table named "user" (escaped for SQL keywords)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID for each new record
    private Long id;

    private String username; // Username for the user
    private String password; // Password for the user

    // Getter and setter for ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

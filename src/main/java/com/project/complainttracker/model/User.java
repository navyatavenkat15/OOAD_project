// ================= USER ENTITY =================
// Represents a user in the system (USER / STAFF / ADMIN)
//
// MVC Layer: Model
//
// Structural Pattern:
// - Domain Model Pattern: Represents real-world entity (User)
//
// GRASP:
// - Information Expert: Stores user-related data
// - High Cohesion: Only responsible for user data
//
// SOLID:
// - SRP: Class handles only user properties
// ==========================================================
package com.project.complainttracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;
    private String email;
    private String password;

    private String role; // USER, STAFF, ADMIN

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
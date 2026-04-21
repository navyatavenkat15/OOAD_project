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
import java.time.LocalDateTime;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complaintId;

    private String description;
    private String status;

    private LocalDateTime createdAt;

    // USER who created complaint
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // STAFF assigned
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;

    // Getters and Setters

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }
}
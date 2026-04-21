// Structural Pattern: Proxy Pattern (via Spring Data JPA)
// The repository acts as a proxy between application and database.
// It controls access to data without exposing actual persistence logic.
//
// GRASP: Low Coupling
// SOLID: Dependency Inversion Principle (DIP)
package com.project.complainttracker.repository;

import com.project.complainttracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
// Behavioral Pattern: Iterator Pattern
// Used when iterating over collections of complaints/users
// via loops (e.g., th:each in Thymeleaf or Java streams).
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    List<User> findByRole(String role);
}

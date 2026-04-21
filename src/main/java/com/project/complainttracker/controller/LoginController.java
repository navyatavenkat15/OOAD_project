// Structural Pattern: Facade Pattern
// The controller acts as a facade by providing a simplified interface
// to complex operations involving service and repository layers.
// It hides internal system complexity from the user interface.
//
// MVC Layer: Controller
// GRASP: Controller
// SOLID: SRP
package com.project.complainttracker.controller;

import com.project.complainttracker.model.User;
import com.project.complainttracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    // Structural Pattern: Adapter (conceptual)
    // Spring adapts different components together via Dependency Injection,
    // allowing incompatible classes to work together seamlessly.
    // Creational: Factory + Singleton via Spring IoC
    // GRASP: Low Coupling
    @Autowired
    private UserService service;

    // Show login page
    // Behavioral Pattern: Command Pattern
    // Each HTTP request acts as a command that triggers a specific action.
    // The controller processes the command and delegates execution to service layer.
    // GRASP: Controller
    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    // Handle login
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

        User user = service.login(email, password);

        if(user != null) {

            session.setAttribute("loggedUser", user);

            if(user.getRole().equals("ADMIN")) {
                return "admin-dashboard";
            }
            else if(user.getRole().equals("STAFF")) {
                return "staff-dashboard";
            }
            else {
                return "user-dashboard";
            }
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
       session.invalidate(); // clears session
       return "redirect:/";
    }

    @GetMapping("/user-dashboard")
    public String userDashboard() {
       return "user-dashboard";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
       return "admin-dashboard";
    }

    @GetMapping("/staff-dashboard")
    public String staffDashboard() {
        return "staff-dashboard";
    }
}
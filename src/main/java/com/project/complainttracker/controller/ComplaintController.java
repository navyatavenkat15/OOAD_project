// Structural Pattern: Facade Pattern
// The controller acts as a facade by providing a simplified interface
// to complex operations involving service and repository layers.
// It hides internal system complexity from the user interface.
//
// MVC Layer: Controller
// GRASP: Controller
// SOLID: SRP
package com.project.complainttracker.controller;

import com.project.complainttracker.model.Complaint;
import com.project.complainttracker.model.User;
import com.project.complainttracker.service.ComplaintService;
import com.project.complainttracker.service.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ComplaintController {

    // Structural Pattern: Adapter (conceptual)
    // Spring adapts different components together via Dependency Injection,
    // allowing incompatible classes to work together seamlessly.
    // Creational: Factory + Singleton via Spring IoC
    // GRASP: Low Coupling
    @Autowired
    private ComplaintService service;

    // Behavioral Pattern: Command Pattern
    // Each HTTP request acts as a command that triggers a specific action.
    // The controller processes the command and delegates execution to service layer.
    // GRASP: Controller
    // Show form
    @GetMapping("/user/add-complaint")
    public String showForm() {
        return "add-complaint";
    }

    // Handle form submission
    @PostMapping("/user/add-complaint")
    public String addComplaint(@RequestParam String description,
                               HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");

        Complaint complaint = new Complaint();
        complaint.setDescription(description);
        complaint.setUser(user);

        service.addComplaint(complaint);

        return "user-dashboard";
    }

    @GetMapping("/user/view-complaints")
    public String viewComplaints(Model model, HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");

        model.addAttribute("complaints", service.getUserComplaints(user));

        return "view-complaints";
    }

    @GetMapping("/admin/assign")
    public String showAssignPage(Model model) {

       model.addAttribute("complaints", service.getAllComplaints());
       model.addAttribute("staffList", userService.getStaffUsers());

       return "assign-complaint";
    }

    // Structural Pattern: Adapter (conceptual)
    // Spring adapts different components together via Dependency Injection,
    // allowing incompatible classes to work together seamlessly.
    // Creational: Factory + Singleton via Spring IoC
    // GRASP: Low Coupling
    @Autowired
    private UserService userService;

    @PostMapping("/admin/assign")
    public String assignComplaint(@RequestParam int complaintId,
                              @RequestParam int staffId) {

        User staff = userService.getStaffUsers()
            .stream()
            .filter(s -> s.getUserId() == staffId)
            .findFirst()
            .orElse(null);

        service.assignStaff(complaintId, staff);

        return "admin-dashboard";
    }

    @GetMapping("/admin/complaints")
    public String viewAllComplaints(Model model) {

        model.addAttribute("complaints", service.getAllComplaints());

        return "admin-complaints";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteComplaint(@PathVariable int id) {

        service.deleteComplaint(id);

        return "redirect:/admin/complaints";
    }

    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {

        Complaint complaint = service.getAllComplaints()
            .stream()
            .filter(c -> c.getComplaintId() == id)
            .findFirst()
            .orElse(null);

        model.addAttribute("complaint", complaint);

        return "edit-complaint";
    }

    @PostMapping("/admin/edit")
    public String updateComplaint(@RequestParam int complaintId,
                              @RequestParam String description,
                              @RequestParam String status) {

        Complaint complaint = service.getAllComplaints()
            .stream()
            .filter(c -> c.getComplaintId() == complaintId)
            .findFirst()
            .orElse(null);

        if (complaint != null) {
        complaint.setDescription(description);
        complaint.setStatus(status);
        service.addComplaint(complaint);
        }

        return "redirect:/admin/complaints";
    }

    @GetMapping("/staff/complaints")
    public String viewStaffComplaints(Model model, HttpSession session) {
 
        User staff = (User) session.getAttribute("loggedUser");

        model.addAttribute("complaints", service.getStaffComplaints(staff));

        return "staff-complaints";
    }

}
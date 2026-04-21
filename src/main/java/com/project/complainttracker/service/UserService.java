// Behavioral Pattern: Chain of Responsibility (conceptual)
// Request flows through layers: Controller → Service → Repository.
// Each layer handles part of the request processing.
// GRASP: Information Expert
// SOLID: SRP
package com.project.complainttracker.service;

import com.project.complainttracker.model.User;
import com.project.complainttracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User login(String email, String password) {
        User user = repo.findByEmail(email);

        if(user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public User saveUser(User user) {
        return repo.save(user);
    }

    public List<User> getStaffUsers() {
        return repo.findByRole("STAFF");
    }
}
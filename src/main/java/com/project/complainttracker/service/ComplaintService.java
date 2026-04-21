// Behavioral Pattern: Chain of Responsibility (conceptual)
// Request flows through layers: Controller → Service → Repository.
// Each layer handles part of the request processing.
// GRASP: Information Expert
// SOLID: SRP
package com.project.complainttracker.service;

import com.project.complainttracker.model.Complaint;
import com.project.complainttracker.model.User;
import com.project.complainttracker.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository repo;

    public Complaint addComplaint(Complaint complaint) {
        complaint.setStatus("Pending");
        complaint.setCreatedAt(LocalDateTime.now());
        return repo.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return repo.findAll();
    }

    public List<Complaint> getUserComplaints(User user) {
        return repo.findByUser(user);
    }

    public List<Complaint> getStaffComplaints(User staff) {
        return repo.findByStaff(staff);
    }

    public void deleteComplaint(int id) {
        repo.deleteById(id);
    }

    public void assignStaff(int complaintId, User staff) {
        Complaint complaint = repo.findById(complaintId).orElse(null);

        if (complaint != null) {
            complaint.setStaff(staff);
            complaint.setStatus("In Progress");
            repo.save(complaint);
        }
    }
}
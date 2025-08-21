package com.neeraj.onlineleavemanagement.Controller;

import com.neeraj.onlineleavemanagement.Repository.LeaveRequestRepository;
import com.neeraj.onlineleavemanagement.entity.LeaveRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class LeaveRequestController {

    private final LeaveRequestRepository leaveRepo;

    public LeaveRequestController(LeaveRequestRepository leaveRepo) {
        this.leaveRepo = leaveRepo;
    }

    // Show leave application form
    @GetMapping("/leave/apply")
    public String showApplyLeavePage() {
        return "apply-leave"; // Loads apply-leave.html
    }

    // Shortcut route
    @GetMapping("/apply-leave")
    public String fallback() {
        return "apply-leave";
    }

    // Handle leave form submission
    @PostMapping("/leave/submit")
    public String submitLeave(@RequestParam String name,
                              @RequestParam int days,
                              @RequestParam(required = false) String reason,
                              RedirectAttributes redirectAttributes) {

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployeeName(name);
        leave.setStartDate(LocalDate.now());
        leave.setEndDate(LocalDate.now().plusDays(days));
        leave.setReason(reason != null ? reason : "No reason provided");
        leave.setStatus("PENDING");

        leaveRepo.save(leave);

        redirectAttributes.addFlashAttribute("message", "Leave submitted successfully!");
        return "redirect:/my-leaves";
    }

    // View all submitted leaves
    @GetMapping("/my-leaves")
    public String showMyLeavesPage(Model model) {
        List<LeaveRequest> leaves = leaveRepo.findAll();
        model.addAttribute("leaves", leaves);
        return "my-leaves"; // Loads my-leaves.html
    }

    // Show pending leaves for managers
    @GetMapping("/leave/pending")
    public String showPendingLeaves(Model model) {
        List<LeaveRequest> pendingLeaves = leaveRepo.findByStatus("PENDING");
        model.addAttribute("pendingLeaves", pendingLeaves);
        return "pending-leaves"; // Loads pending-leaves.html
    }

    // Approve leave
    @PostMapping("/leave/approve/{id}")
    public String approveLeave(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        LeaveRequest leave = leaveRepo.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus("APPROVED");
            leaveRepo.save(leave);
            redirectAttributes.addFlashAttribute("message", "Leave approved successfully.");
        }
        return "redirect:/leave/pending";
    }

    // Reject leave
    @PostMapping("/leave/reject/{id}")
    public String rejectLeave(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        LeaveRequest leave = leaveRepo.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus("REJECTED");
            leaveRepo.save(leave);
            redirectAttributes.addFlashAttribute("message", "Leave rejected.");
        }
        return "redirect:/leave/pending";
    }
}

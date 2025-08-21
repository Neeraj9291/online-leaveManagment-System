package com.neeraj.onlineleavemanagement.Service;



import com.neeraj.onlineleavemanagement.Repository.LeaveRequestRepository;
import com.neeraj.onlineleavemanagement.entity.LeaveRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

    // Inject the repository, not the entity
    private final LeaveRequestRepository leaveRequestRepository;

    // ✅ Correct constructor injection
    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    public LeaveRequest createLeave(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

    public LeaveRequest getLeaveById(Long id) {
        return leaveRequestRepository.findById(id).orElse(null);
    }

    public LeaveRequest updateLeaveStatus(Long id, String status) {
        Optional<LeaveRequest> optionalLeave = leaveRequestRepository.findById(id);
        if (optionalLeave.isPresent()) {
            LeaveRequest leave = optionalLeave.get();
            leave.setStatus(status);
            return leaveRequestRepository.save(leave);
        }
        return null;
    }

    public boolean deleteLeave(Long id) {
        if (leaveRequestRepository.existsById(id)) {
            leaveRequestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

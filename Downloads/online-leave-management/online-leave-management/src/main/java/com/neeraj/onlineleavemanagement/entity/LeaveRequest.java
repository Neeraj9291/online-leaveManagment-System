package com.neeraj.onlineleavemanagement.entity;
import com.neeraj.onlineleavemanagement.entity.LeaveRequest;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;

    private String status; // PENDING, APPROVED, REJECTED

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
	public void setName1(String name) {
		// TODO Auto-generated method stub
		
	}
	
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}
	public void setDays(int days) {
		// TODO Auto-generated method stub
		
	}
}

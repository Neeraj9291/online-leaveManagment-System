package com.neeraj.onlineleavemanagement.Controller;
// Do NOT import jakarta.persistence.*

import java.time.LocalDate;

// REMOVE @Entity
// REMOVE @Table
public class LeaveRequest {

    private Long id;

    private String employeeName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;

    private String status; // PENDING, APPROVED, REJECTED

    // Getters & Setters...
}

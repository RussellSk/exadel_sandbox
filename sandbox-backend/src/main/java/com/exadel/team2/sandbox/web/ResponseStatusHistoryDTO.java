package com.exadel.team2.sandbox.web;

import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseStatusHistoryDTO {

    private Long id;

    private Status status;

//    private Candidate candidate;

    private EmployeeEntity employee;

    private String changeNote;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

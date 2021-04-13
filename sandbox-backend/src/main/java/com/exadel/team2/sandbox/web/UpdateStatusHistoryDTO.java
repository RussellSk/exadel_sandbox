package com.exadel.team2.sandbox.web;

import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateStatusHistoryDTO {

    private Long statusId;

//    private Candidate candidate;

    private Long employeeId;

    private String changeNote;

    private LocalDateTime updatedAt;
}

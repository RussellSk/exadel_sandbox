package com.exadel.team2.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventСreateDTO {

    private Long Id;
    private Long imageId;     // сюда ImageDTO
//    private Long employeeId;  // EmployeeDTO
    private Long eventTypeId; // EventTypeDTO
    private String shortDescription;
//    duration +
    private String fullDescription;
    private LocalDate startDate;
    private LocalDate deadline;
    private String location;
    private String candidateRequirements;
    private LocalDateTime createdAt;

}

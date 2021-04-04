package com.exadel.team2.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdateDTO {

    private Long Id;
    private Long imageId;     // сюда ImageDTO
    //    private Long employeeId;  // EmployeeDTO
    private Long eventTypeId; // EventTypeDTO
    private String shortdescription;
    //    duration +
    private String fullDescription;
    private LocalDate startDate;
    private LocalDate deadline;
    private String location;
    private String candidateRequirements;
    private LocalDateTime updatedAt;

}
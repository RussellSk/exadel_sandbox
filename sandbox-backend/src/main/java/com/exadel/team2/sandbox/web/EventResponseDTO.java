package com.exadel.team2.sandbox.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {

    private Long id;
    private ImageCreateDTO image;
    private EmployeeDTO employee;
    private EventTypeCreateDTO eventType;
    private String shortDescription;
    //    duration +
    private String fullDescription;
    private LocalDate startDate;
    private LocalDate deadline;
    private String location;
    private String candidateRequirements;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
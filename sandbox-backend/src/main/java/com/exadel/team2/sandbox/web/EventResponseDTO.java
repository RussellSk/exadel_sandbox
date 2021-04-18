package com.exadel.team2.sandbox.web;

import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {

    private Long evId;
    private ImageResponseDTO image;
    private ResponseEmployeeDto employee;
    private EventTypeResponseDTO eventType;
    private ResponseEmployeeDto creatorEvent;
    private LocalDate evStartDate;
    private String evDuration;
    private LocalDate evDeadline;
    @NotNull
    private LocalDate evDateOfEndAccept;
    @NotNull
    private String evLocation;
    private LocalDateTime evCreatedAt;
    private LocalDateTime evUpdatedAt;
    private String evCandidateRequirements;
}
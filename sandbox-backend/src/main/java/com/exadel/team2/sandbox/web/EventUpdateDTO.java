package com.exadel.team2.sandbox.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdateDTO {

    private Long imageId;
    private Long employeeId;
    private Long eventTypeId;
    private LocalDate evStartDate;
    
    @Size(min = 1, max = 50, message = "Duration must be between 1 and 50 characters")
    private String evDuration;
    private LocalDate evDeadline;
    @NotNull
    private String evLocation;
    private String evCandidateRequirements;

}
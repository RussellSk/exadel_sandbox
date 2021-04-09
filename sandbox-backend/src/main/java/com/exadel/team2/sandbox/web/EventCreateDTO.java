package com.exadel.team2.sandbox.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateDTO {

    private Long imageId;
    private Long employeeId;
    private Long eventTypeId;
    private LocalDate evStartDate;
    private String evDuration;
    private LocalDate evDeadline;
    @NotNull
    private String evLocation;
    private String evCandidateRequirements;
}

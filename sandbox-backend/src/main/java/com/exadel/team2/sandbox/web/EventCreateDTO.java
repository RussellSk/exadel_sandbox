package com.exadel.team2.sandbox.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateDTO {

    private Long imageId;
    private Long employeeId;
    private Long evenTypeId;
    private String shortDescription;
//    duration +
    private String fullDescription;
    private LocalDate startDate;
    private LocalDate deadline;
    private String location;
    private String candidateRequirements;

}

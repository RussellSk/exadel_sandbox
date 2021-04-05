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
    private Long evenTypeId;

    @NotNull
    private String shortDescription;
//    duration +

    @NotNull
    private String fullDescription;
    private LocalDate startDate;
    private LocalDate deadline;

    @NotNull
    private String location;
    private String candidateRequirements;

}

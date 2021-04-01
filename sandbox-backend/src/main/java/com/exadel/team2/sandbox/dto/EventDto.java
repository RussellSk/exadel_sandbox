package com.exadel.team2.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private Long evId;
    private Long imageId;
//    private Employee employee;
    private Long eventTypeId;
    private String evShortDescription;
    private String evFullDescription;
    private LocalDate evStartDate;
    private LocalDate evDeadline;
    private String evLocation;
    private String evCandidateRequirements;

}

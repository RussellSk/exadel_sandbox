package com.exadel.team2.sandbox.web.candidate_event;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class CreateCandidateEventDto {
    @NotNull(message = "idCandidate cannot be null")
    private Long idCandidate;
    @NotNull(message = "idEvent cannot be null")
    private Long idEvent;


}

package com.exadel.team2.sandbox.web.candidate_event;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class UpdateCandidateEventDto {
    @NotNull(message = "idCandidate cannot be null")
    private long idCandidate;
    @NotNull(message = "idEvent cannot be null")
    private long idEvent;
}

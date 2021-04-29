package com.exadel.team2.sandbox.web.interview_feedback;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateInterviewFeedbackDto {
    @NotNull(message = "idEmployee cannot be null")
    private Long idEmployee;
    @NotNull(message = "idCandidate cannot be null")
    private Long idCandidate;
    @NotNull(message = "feedback cannot be null")
    private String feedback;

}

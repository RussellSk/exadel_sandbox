package com.exadel.team2.sandbox.web.interview_feedback;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateInterviewFeedbackDto {
    @NotNull(message = "idEmployee cannot be null")
    private long idEmployee;
    @NotNull(message = "idCandidate cannot be null")
    private long idCandidate;
    @NotNull(message = "feedback cannot be null")
    private String feedback;

}

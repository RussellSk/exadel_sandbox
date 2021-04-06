package com.exadel.team2.sandbox.web.interview_feedback;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateInterviewFeedbackDto {
    private long idEmployee;
    private long idCandidate;
    private String feedback;
    private LocalDateTime dateOfUpdate;
}

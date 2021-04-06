package com.exadel.team2.sandbox.web.interview_feedback;

import lombok.Data;
@Data
public class UpdateInterviewFeedbackDto {
    private long idEmployee;
    private long idCandidate;
    private String feedback;
}

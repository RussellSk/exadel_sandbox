package com.exadel.team2.sandbox.web.interview_feedback;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseInterviewFeedbackDto {
    private long id;
    private String feedback;
    private LocalDateTime dateOfCreate;
   // private ResponseEmployeeDTO employee;
    //private ResponseCandidateDTO candidate;
}

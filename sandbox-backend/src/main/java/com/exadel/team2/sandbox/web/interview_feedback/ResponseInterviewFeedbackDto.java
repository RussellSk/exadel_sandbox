package com.exadel.team2.sandbox.web.interview_feedback;

import lombok.Data;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ResponseInterviewFeedbackDto {
    @NotNull
    private long id;
    @NotNull
    private String feedback;
    @NotNull
    private LocalDateTime dateOfCreate;
    @NotNull
    private LocalDateTime dateOfUpdate;
    @NotNull
    private ResponseEmployeeDTO employee;
    @NotNull
    private ResponseCandidateDTO candidate;
}

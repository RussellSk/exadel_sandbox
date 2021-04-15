package com.exadel.team2.sandbox.web.statushistory;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateStatusHistoryDTO {

    @NotNull(message = "Status id cannot be null")
    private Long statusId;

    @NotNull(message = "Candidates id cannot be null")
    private Long candidateId;

    @NotNull(message = "Employee id cannot be null")
    private Long employeeId;

    private String changeNote;

    private LocalDateTime createdAt;
}

package com.exadel.team2.sandbox.web.statushistory;

import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.web.GeneralDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.status.ResponseStatusDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ResponseStatusHistoryDTO implements GeneralDto {

    @NotNull
    private Long id;

    @NotNull
    private ResponseStatusDTO status;

    @NotNull
    private CandidateResponseDTO candidate;

    @NotNull
    private ResponseEmployeeDto employee;

    @NotNull
    private String changeNote;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;
}

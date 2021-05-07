package com.exadel.team2.sandbox.web.candidate_event;

import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.entity.EventEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ResponseCandidateEventDto {
    @NotNull
    private Long id;
    @NotNull
    private EventEntity event;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private CandidateResponseDTO candidate;

}

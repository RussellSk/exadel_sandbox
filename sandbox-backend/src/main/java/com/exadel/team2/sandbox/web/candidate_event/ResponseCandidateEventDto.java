package com.exadel.team2.sandbox.web.candidate_event;

import com.exadel.team2.sandbox.entity.EventEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class ResponseCandidateEventDto {
    @NotNull
    private long id;
    @NotNull
    private EventEntity event;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private List<CandidateEntity> candidates;

}

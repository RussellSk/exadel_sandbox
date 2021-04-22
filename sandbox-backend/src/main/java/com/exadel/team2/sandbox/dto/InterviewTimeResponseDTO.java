package com.exadel.team2.sandbox.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class InterviewTimeResponseDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long evId;

    @NotNull
    private Long cnId;

    @NotNull
    private Long empId;

    @NotNull
    private LocalDateTime beginDate;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

}

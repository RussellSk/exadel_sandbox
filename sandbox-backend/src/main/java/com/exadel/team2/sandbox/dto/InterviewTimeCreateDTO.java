package com.exadel.team2.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InterviewTimeCreateDTO {

    @NotNull
    private Long evId;

    @NotNull
    private Long cnId;

    @NotNull
    private Long empId;

    @NotNull
    private LocalDateTime beginDate;

}

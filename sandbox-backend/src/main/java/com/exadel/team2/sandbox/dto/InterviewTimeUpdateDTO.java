package com.exadel.team2.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InterviewTimeUpdateDTO {

    @Id
    private Long itmId;

    private Long evId;

    private Long cnId;

    private Long empId;

    private LocalDateTime beginDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

package com.exadel.team2.sandbox.web.employee_availability_time;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class ResponseCrossedTimeSlots {
    @NotNull
    private Long employeeId;

    @NotNull
    private Long candidateId;

    @NotNull
    private List<TimeId> suitableTimeSlots = new ArrayList<>();
}

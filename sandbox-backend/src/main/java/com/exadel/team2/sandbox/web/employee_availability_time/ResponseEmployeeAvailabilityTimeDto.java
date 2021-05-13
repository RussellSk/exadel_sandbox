package com.exadel.team2.sandbox.web.employee_availability_time;

import com.exadel.team2.sandbox.web.GeneralDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseEmployeeAvailabilityTimeDto implements GeneralDto {
    @NotNull
    private Long employeeId;

    @NotNull
    private List<TimeId> availableTimeSlots = new ArrayList<>();
}

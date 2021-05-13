package com.exadel.team2.sandbox.web.employee_availability_time;

import com.exadel.team2.sandbox.web.GeneralDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateEmployeeAvailabilityTimeDto implements GeneralDto {

    @NotNull(message = "employeeId cannot be null")
    private Long employeeId;

    @NotNull(message = "availableTimeSlots cannot be null")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private List<LocalDateTime> availableTimeSlots;
}

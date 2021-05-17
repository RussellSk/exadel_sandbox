package com.exadel.team2.sandbox.web.canidate_availability_time;

import com.exadel.team2.sandbox.web.GeneralDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class UpdateCandidateAvailabilityTime implements GeneralDto {

    @NotNull(message = "availabilityTimeSlots can't be null")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private List<LocalDateTime> availabilityTimeSlots;
}

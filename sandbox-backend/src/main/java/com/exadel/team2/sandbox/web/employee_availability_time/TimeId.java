package com.exadel.team2.sandbox.web.employee_availability_time;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TimeId {
    @NotNull
    private final Long slotId;

    @NotNull
    private final LocalDateTime dateTime;
}

package com.exadel.team2.sandbox.web.canidate_availability_time;

import com.exadel.team2.sandbox.web.GeneralDto;
import com.exadel.team2.sandbox.web.employee_availability_time.TimeId;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseCandidateAvailabilityTimeDto implements GeneralDto {

    @NotNull
    private Long candidateId;

    @NotNull
    private List<TimeId> availabilityTimeSlots = new ArrayList<>();
}

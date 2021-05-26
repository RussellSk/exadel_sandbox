package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.canidate_availability_time.CreateCandidateAvailabilityTimeDto;
import com.exadel.team2.sandbox.web.canidate_availability_time.ResponseCandidateAvailabilityTimeDto;
import com.exadel.team2.sandbox.web.canidate_availability_time.UpdateCandidateAvailabilityTime;

public interface CandidateAvailabilityTimeService extends GeneralService<ResponseCandidateAvailabilityTimeDto,
        CreateCandidateAvailabilityTimeDto,
        UpdateCandidateAvailabilityTime> {

    ResponseCandidateAvailabilityTimeDto getByCandidateId(Long id);
    void deleteAllByCandidateId(Long id);
}

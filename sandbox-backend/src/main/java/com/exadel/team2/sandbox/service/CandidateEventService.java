package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.candidate_event.ResponseCandidateEventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CandidateEventService {
    ResponseCandidateEventDto getById(Long id);

    void delete(Long id);

    Page<ResponseCandidateEventDto> getAllPageable(Pageable pageable, String search);

}

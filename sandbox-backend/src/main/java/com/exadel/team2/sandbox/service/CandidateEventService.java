package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.candidate_event.CreateCandidateEventDto;
import com.exadel.team2.sandbox.web.candidate_event.ResponseCandidateEventDto;
import cz.jirutka.rsql.parser.RSQLParserException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CandidateEventService {
    ResponseCandidateEventDto getById(Long id);

    List<ResponseCandidateEventDto> getAll();

    ResponseCandidateEventDto save(CreateCandidateEventDto candidateEventDto);

    void delete(Long id);

    Page<ResponseCandidateEventDto> getAllPageable(Pageable pageable, String search);
}

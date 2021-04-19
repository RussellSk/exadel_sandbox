package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.CandidateCreateDTO;
import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.dto.CandidateUpdateDTO;
import cz.jirutka.rsql.parser.RSQLParserException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CandidateService {

    CandidateResponseDTO findById(Long id);

    List<CandidateResponseDTO> getAllPageable(Pageable pageable, String search);

    CandidateCreateDTO save(CandidateCreateDTO candidateCreateDTO) throws RSQLParserException;

    CandidateUpdateDTO update(Long id, CandidateUpdateDTO candidateUpdateDTO) throws RSQLParserException;

    void delete(Long id);

}

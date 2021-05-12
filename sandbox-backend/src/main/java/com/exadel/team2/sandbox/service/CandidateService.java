package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.CandidateCreateDTO;
import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.dto.CandidateUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CandidateService {

    CandidateResponseDTO findById(Long id);

    Page<CandidateResponseDTO> getAllPageable(Pageable pageable, String search);

    CandidateResponseDTO save(CandidateCreateDTO candidateCreateDTO);

    CandidateUpdateDTO update(Long id, CandidateUpdateDTO candidateUpdateDTO);

    void delete(Long id);

}

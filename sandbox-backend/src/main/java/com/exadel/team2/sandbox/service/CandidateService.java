package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.CandidateCreateDTO;
import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.dto.CandidateUpdateDTO;
import com.exadel.team2.sandbox.entity.CandidateEntity;

import java.util.List;

public interface CandidateService {

    CandidateResponseDTO findById(Long id);

    List<CandidateResponseDTO> getAll();

    CandidateCreateDTO save(CandidateCreateDTO candidateCreateDTO);

    CandidateUpdateDTO update(Long id, CandidateUpdateDTO candidateUpdateDTO);

    void delete(Long id);

}

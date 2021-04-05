package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.entity.CandidateEntity;

import java.util.List;

public interface CandidateService {

    CandidateResponseDTO findById(Long id);

    List<CandidateEntity> getAll();

    CandidateEntity save(CandidateEntity candidateEntity);

    CandidateEntity update(CandidateEntity candidateEntity);

    void delete(Long id);

}

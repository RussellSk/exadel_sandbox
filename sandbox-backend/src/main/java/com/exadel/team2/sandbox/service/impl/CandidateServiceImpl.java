package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.CandidateDAO;
import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.entity.CandidateEntity;
import com.exadel.team2.sandbox.mapper.Release;
import com.exadel.team2.sandbox.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateDAO candidateDAO;
    private final Release release;

    @Override
    public CandidateResponseDTO findById(Long id) {
//        System.out.println(candidateDAO.findById(id).orElse(null));
        return release.convertTo(candidateDAO.findById(id).orElse(null), CandidateResponseDTO.class);
    }

    @Override
    public List<CandidateEntity> getAll() {
        return candidateDAO.findAll();
    }

    @Override
    public CandidateEntity save(CandidateEntity candidateEntity) {
        return candidateDAO.save(candidateEntity);
    }

    @Override
    public CandidateEntity update(CandidateEntity candidateEntity) {
        return candidateDAO.save(candidateEntity);
    }

    @Override
    public void delete(Long id) {
        candidateDAO.deleteById(id);
    }
}

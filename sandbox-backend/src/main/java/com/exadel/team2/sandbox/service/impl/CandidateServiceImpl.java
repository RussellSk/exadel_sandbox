package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.CandidateDAO;
import com.exadel.team2.sandbox.dto.CandidateCreateDTO;
import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.dto.CandidateUpdateDTO;
import com.exadel.team2.sandbox.entity.CandidateEntity;
import com.exadel.team2.sandbox.mapper.Release;
import com.exadel.team2.sandbox.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateDAO candidateDAO;
    private final Release release;

    @Override
    public CandidateResponseDTO findById(Long id) {
        return release.convertTo(candidateDAO.findById(id).orElse(null),
                CandidateResponseDTO.class);
    }

    @Override
    public List<CandidateResponseDTO> getAll() {
        return candidateDAO.findAll().stream()
                .map((CandidateEntity candidateEntity) ->
                        (CandidateResponseDTO) release.convertTo(
                                candidateEntity, CandidateResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CandidateCreateDTO save(CandidateCreateDTO candidateCreateDTO) {
        return release.convertTo(candidateDAO.save(release.convertTo(candidateCreateDTO, CandidateEntity.class)),
                CandidateCreateDTO.class);
    }

    @Override
    public CandidateUpdateDTO update(CandidateUpdateDTO candidateUpdateDTO) {
        return release.convertTo(candidateDAO.save(
                release.convertTo(candidateUpdateDTO, CandidateEntity.class)),
                CandidateUpdateDTO.class);
    }

    @Override
    public void delete(Long id) {
        candidateDAO.deleteById(id);
    }
}

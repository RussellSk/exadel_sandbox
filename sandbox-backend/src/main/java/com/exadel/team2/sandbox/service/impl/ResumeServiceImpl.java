package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.ResumeDAO;
import com.exadel.team2.sandbox.dto.ResumeCreateDTO;
import com.exadel.team2.sandbox.dto.ResumeResponseDTO;
import com.exadel.team2.sandbox.dto.ResumeUpdateDTO;
import com.exadel.team2.sandbox.entity.ResumeEntity;
import com.exadel.team2.sandbox.mapper.Release;
import com.exadel.team2.sandbox.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDAO resumeDAO;
    private final Release release;

    @Override
    public ResumeResponseDTO getById(Long id) {
        return release.convertTo(
                resumeDAO.findById(id).orElse(null),
                ResumeResponseDTO.class);
    }

    @Override
    public List<ResumeResponseDTO> getAll() {
        return resumeDAO.findAll().stream().map((ResumeEntity entity) ->
                (ResumeResponseDTO) release.convertTo(entity, ResumeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResumeCreateDTO save(ResumeCreateDTO resumeCreateDTO) {
        return release.convertTo(
                resumeDAO.save(release.convertTo(resumeCreateDTO, ResumeEntity.class)),
                        ResumeCreateDTO.class);
    }

    @Override
    public ResumeUpdateDTO update(ResumeUpdateDTO resumeUpdateDTO) {
        return release.convertTo(
                resumeDAO.save(release.convertTo(resumeUpdateDTO, ResumeEntity.class)),
                ResumeUpdateDTO.class);
    }

    @Override
    public void delete(Long id) {
        resumeDAO.deleteById(id);
    }
}

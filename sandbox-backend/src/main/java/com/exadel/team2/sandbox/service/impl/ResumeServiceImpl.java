package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.ResumeDAO;
import com.exadel.team2.sandbox.dto.ResumeCreateDTO;
import com.exadel.team2.sandbox.dto.ResumeResponseDTO;
import com.exadel.team2.sandbox.dto.ResumeUpdateDTO;
import com.exadel.team2.sandbox.entity.ResumeEntity;
import com.exadel.team2.sandbox.mapper.Mapper;
import com.exadel.team2.sandbox.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDAO resumeDAO;
    private final Mapper mapper;

    @Override
    public ResumeResponseDTO getById(Long id) {
        return mapper.convertTo(
                resumeDAO.findById(id).orElse(null),
                ResumeResponseDTO.class);
    }

    @Override
    public List<ResumeResponseDTO> getAll() {
        return resumeDAO.findAll().stream().map((ResumeEntity entity) ->
                (ResumeResponseDTO) mapper.convertTo(entity, ResumeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResumeCreateDTO save(ResumeCreateDTO resumeCreateDTO) {
        return mapper.convertTo(
                resumeDAO.save(mapper.convertTo(resumeCreateDTO, ResumeEntity.class)),
                        ResumeCreateDTO.class);
    }

    @Override
    public ResumeUpdateDTO update(Long id, ResumeUpdateDTO resumeUpdateDTO) {
        ResumeEntity resumeEntity = resumeDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resume not found"));

        if (resumeUpdateDTO.getPath() != null) {
            resumeEntity.setPath(resumeUpdateDTO.getPath());
        }

        if (resumeUpdateDTO.getLink() != null) {
            resumeEntity.setPath(resumeUpdateDTO.getLink());
        }

        if (resumeUpdateDTO.getName() != null) {
            resumeEntity.setName(resumeUpdateDTO.getName());
        }

        if (resumeUpdateDTO.getExt() != null) {
            resumeEntity.setExt(resumeUpdateDTO.getExt());
        }

        if (resumeUpdateDTO.getSize() != null) {
            resumeEntity.setSize(resumeUpdateDTO.getSize());
        }

        return mapper.convertTo(
                resumeDAO.save(mapper.convertTo(resumeUpdateDTO, ResumeEntity.class)),
                ResumeUpdateDTO.class);
    }

    @Override
    public void delete(Long id) {
        resumeDAO.deleteById(id);
    }
}

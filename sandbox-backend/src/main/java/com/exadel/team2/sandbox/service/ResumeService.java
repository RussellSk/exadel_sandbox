package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.ResumeCreateDTO;
import com.exadel.team2.sandbox.dto.ResumeResponseDTO;
import com.exadel.team2.sandbox.dto.ResumeUpdateDTO;
import com.exadel.team2.sandbox.entity.ResumeEntity;

import java.util.List;

public interface ResumeService {

    ResumeResponseDTO getById(Long id);

    List<ResumeResponseDTO> getAll();

    ResumeCreateDTO save(ResumeCreateDTO resumeCreateDTO);

    ResumeUpdateDTO update(Long id, ResumeUpdateDTO resumeUpdateDTO);

    void delete(Long id);
}

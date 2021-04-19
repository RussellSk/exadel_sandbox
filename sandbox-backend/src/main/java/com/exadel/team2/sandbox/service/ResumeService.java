package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.ResumeCreateDTO;
import com.exadel.team2.sandbox.dto.ResumeResponseDTO;
import com.exadel.team2.sandbox.dto.ResumeUpdateDTO;
import cz.jirutka.rsql.parser.RSQLParserException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResumeService {

    ResumeResponseDTO getById(Long id);

    List<ResumeResponseDTO> getAllPageable(Pageable pageable, String search);

    ResumeCreateDTO save(ResumeCreateDTO resumeCreateDTO) throws RSQLParserException;

    ResumeUpdateDTO update(Long id, ResumeUpdateDTO resumeUpdateDTO) throws RSQLParserException;

    void delete(Long id);
}

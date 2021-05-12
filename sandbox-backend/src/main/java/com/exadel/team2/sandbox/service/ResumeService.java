package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResumeService {

    ResumeResponseDTO getById(Long id);

    List<ResumeResponseDTO> getAllPageable(Pageable pageable, String search);

    ResumeResponseDTO save(Long candidateId, MultipartFile file, String link);

    ResumeUpdateDTO update(Long id, ResumeUpdateDTO resumeUpdateDTO);

    void delete(Long id);
}

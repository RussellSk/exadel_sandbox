package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.InterviewTimeCreateDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeResponseDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeUpdateDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterviewTimeService {

    InterviewTimeResponseDTO findById(Long id);

    List<InterviewTimeResponseDTO> getAll(Pageable pageable);

    InterviewTimeCreateDTO save(InterviewTimeCreateDTO interviewTimeCreateDTO);

    InterviewTimeUpdateDTO update(Long id, InterviewTimeUpdateDTO interviewTimeUpdateDTO);

    void delete(Long id);
}

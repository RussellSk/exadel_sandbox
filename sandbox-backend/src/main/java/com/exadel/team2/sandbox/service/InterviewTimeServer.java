package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.InterviewTimeCreateDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeResponseDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeUpdateDTO;

import java.util.List;

public interface InterviewTimeServer {

    InterviewTimeResponseDTO findById(Long id);

    List<InterviewTimeResponseDTO> getAll();

    InterviewTimeCreateDTO save(InterviewTimeCreateDTO interviewTimeCreateDTO);

    InterviewTimeUpdateDTO update(Long id, InterviewTimeUpdateDTO interviewTimeUpdateDTO);

    void delete(Long id);
}

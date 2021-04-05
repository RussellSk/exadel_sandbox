package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.InterviewTimeDAO;
import com.exadel.team2.sandbox.dto.InterviewTimeCreateDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeResponseDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeUpdateDTO;
import com.exadel.team2.sandbox.entity.InterviewTimeEntity;
import com.exadel.team2.sandbox.mapper.Release;
import com.exadel.team2.sandbox.service.InterviewTimeServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class InterviewTimeServiceImpl implements InterviewTimeServer {

    private final InterviewTimeDAO interviewTimeDAO;
    private final Release release;

    @Override
    public InterviewTimeResponseDTO findById(Long id) {
        return release.convertTo(interviewTimeDAO.findById(id).orElse(null),
                InterviewTimeResponseDTO.class);
    }

    @Override
    public List<InterviewTimeResponseDTO> getAll() {
        return interviewTimeDAO.findAll()
                .stream().map((InterviewTimeEntity interviewTimeEntity) ->
                        (InterviewTimeResponseDTO) release
                                .convertTo(interviewTimeEntity, InterviewTimeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public InterviewTimeCreateDTO save(InterviewTimeCreateDTO interviewTimeCreateDTO) {
        return release.convertTo(interviewTimeDAO
                .save(release.convertTo(interviewTimeCreateDTO, InterviewTimeEntity.class)),
                InterviewTimeCreateDTO.class);
    }

    @Override
    public InterviewTimeUpdateDTO update(InterviewTimeUpdateDTO interviewTimeUpdateDTO) {
        return release.convertTo(interviewTimeDAO
                        .save(release.convertTo(interviewTimeUpdateDTO, InterviewTimeEntity.class)),
                InterviewTimeUpdateDTO.class);
    }

    @Override
    public void delete(Long id) {
        interviewTimeDAO.deleteById(id);
    }
}

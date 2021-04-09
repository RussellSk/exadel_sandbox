package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.InterviewTimeDAO;
import com.exadel.team2.sandbox.dto.InterviewTimeCreateDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeResponseDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeUpdateDTO;
import com.exadel.team2.sandbox.entity.InterviewTimeEntity;
import com.exadel.team2.sandbox.mapper.ModelMap;
import com.exadel.team2.sandbox.service.InterviewTimeService;
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
public class InterviewTimeServiceImpl implements InterviewTimeService {

    private final InterviewTimeDAO interviewTimeDAO;
    private final ModelMap modelMap;

    @Override
    public InterviewTimeResponseDTO findById(Long id) {
        return modelMap.convertTo(interviewTimeDAO.findById(id).orElse(null),
                InterviewTimeResponseDTO.class);
    }

    @Override
    public List<InterviewTimeResponseDTO> getAll() {
        return interviewTimeDAO.findAll()
                .stream().map((InterviewTimeEntity interviewTimeEntity) ->
                        (InterviewTimeResponseDTO) modelMap
                                .convertTo(interviewTimeEntity, InterviewTimeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public InterviewTimeCreateDTO save(InterviewTimeCreateDTO interviewTimeCreateDTO) {
        return modelMap.convertTo(interviewTimeDAO
                .save(modelMap.convertTo(interviewTimeCreateDTO, InterviewTimeEntity.class)),
                InterviewTimeCreateDTO.class);
    }

    @Override
    public InterviewTimeUpdateDTO update(Long id, InterviewTimeUpdateDTO interviewTimeUpdateDTO) {
        InterviewTimeEntity interviewTimeEntity = interviewTimeDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The meeting didn't arrange"));

        if (interviewTimeUpdateDTO.getEvId() != null) {
            interviewTimeEntity.setEvId(interviewTimeUpdateDTO.getEvId());
        }

        if (interviewTimeUpdateDTO.getCnId() != null) {
            interviewTimeEntity.setCnId(interviewTimeUpdateDTO.getCnId());
        }

        if (interviewTimeUpdateDTO.getEmpId() != null) {
            interviewTimeEntity.setEmpId(interviewTimeUpdateDTO.getEmpId());
        }

        if (interviewTimeUpdateDTO.getBeginDate() != null) {
            interviewTimeEntity.setBeginDate(interviewTimeUpdateDTO.getBeginDate());
        }

        interviewTimeEntity.setUpdatedAt(interviewTimeUpdateDTO.getUpdatedAt());

        return modelMap.convertTo(interviewTimeDAO
                        .save(interviewTimeEntity), InterviewTimeUpdateDTO.class);
    }

    @Override
    public void delete(Long id) {
        interviewTimeDAO.deleteById(id);
    }
}

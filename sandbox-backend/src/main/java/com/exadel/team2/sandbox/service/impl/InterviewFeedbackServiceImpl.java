package com.exadel.team2.sandbox.service.impl;


import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.dao.InterviewFeedbackDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.InterviewFeedbackEntity;
import com.exadel.team2.sandbox.mapper.InterviewFeedbackMapper;
import com.exadel.team2.sandbox.service.InterviewFeedbackService;
import com.exadel.team2.sandbox.web.interview_feedback.CreateInterviewFeedbackDTO;
import com.exadel.team2.sandbox.web.interview_feedback.ResponseInterviewFeedbackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class InterviewFeedbackServiceImpl implements InterviewFeedbackService {

    private final InterviewFeedbackDAO interviewFeedbackDAO;
    private final InterviewFeedbackMapper interviewFeedbackMapper;
    private final EmployeeDAO employeeDAO;
    //private final CandidateDAO candidateDAO;

    @Override
    public ResponseInterviewFeedbackDto getById(Long id) {
        InterviewFeedbackEntity interviewFeedbackEntity=interviewFeedbackDAO.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback Not Found"));
        return interviewFeedbackMapper.convertEntityToDto(interviewFeedbackEntity);
    }

    @Override
    public List<ResponseInterviewFeedbackDto> getAll() {
        List<InterviewFeedbackEntity> interviewFeedbackEntities=interviewFeedbackDAO.findAll();
        if (interviewFeedbackEntities.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Content");
        }
        return interviewFeedbackEntities.stream()
                .map(interviewFeedbackMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseInterviewFeedbackDto save(CreateInterviewFeedbackDTO createInterviewFeedbackDTO) {

        InterviewFeedbackEntity interviewFeedbackEntity = interviewFeedbackMapper.convertDtoToEntity(createInterviewFeedbackDTO);

        EmployeeEntity employeeEntity = employeeDAO.findById(createInterviewFeedbackDTO.getIdEmployee())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        interviewFeedbackEntity.setEmployee(employeeEntity);






        interviewFeedbackEntity.setFeedback(createInterviewFeedbackDTO.getFeedback());
        interviewFeedbackEntity.setDateOfCreate(LocalDateTime.now());
        interviewFeedbackEntity.setDateOfUpdate(LocalDateTime.now());
        interviewFeedbackDAO.save(interviewFeedbackEntity);

        return interviewFeedbackMapper.convertEntityToDto(interviewFeedbackEntity);
    }

    @Override
    public InterviewFeedbackEntity update(InterviewFeedbackEntity interviewFeedbackEntity) {
        return dao.save(interviewFeedbackEntity);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Page<ResponseInterviewFeedbackDto> getAllPageable(Pageable pageable) {
        return null;
    }
}

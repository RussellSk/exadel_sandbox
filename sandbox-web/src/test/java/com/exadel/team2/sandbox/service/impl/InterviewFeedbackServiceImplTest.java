package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.BaseTestClass;
import com.exadel.team2.sandbox.dao.CandidateDAO;
import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.dao.InterviewFeedbackDAO;
import com.exadel.team2.sandbox.entity.CandidateEntity;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.InterviewFeedbackEntity;
import com.exadel.team2.sandbox.exceptions.NoSuchException;
import com.exadel.team2.sandbox.mapper.InterviewFeedbackMapper;
import com.exadel.team2.sandbox.service.InterviewFeedbackService;
import com.exadel.team2.sandbox.web.interview_feedback.CreateInterviewFeedbackDto;
import com.exadel.team2.sandbox.web.interview_feedback.ResponseInterviewFeedbackDto;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {InterviewFeedbackServiceImpl.class, InterviewFeedbackMapper.class})
class InterviewFeedbackServiceImplTest extends BaseTestClass {

    private static final Long IFB_ID = 1L;
    private static final Long ID = 2L;

    @Autowired
    private InterviewFeedbackService interviewFeedbackService;

    @MockBean
    private EmployeeDAO employeeDAO;

    @MockBean
    private CandidateDAO candidateDAO;

    @MockBean
    private InterviewFeedbackDAO interviewFeedbackDAO;

    @Test
    void saveWhenIFB_NotNull() {
        InterviewFeedbackEntity interviewFeedbackEntity = createOptional().get();
        interviewFeedbackEntity.setId(null);
        when(candidateDAO.findById(ID)).thenReturn(Optional.of(interviewFeedbackEntity.getCandidate()));
        when(employeeDAO.findById(ID)).thenReturn(Optional.of(interviewFeedbackEntity.getEmployee()));
        when(interviewFeedbackDAO.save(interviewFeedbackEntity)).thenReturn(interviewFeedbackEntity);
        ResponseInterviewFeedbackDto responseInterviewFeedbackDto = interviewFeedbackService.save(createIFB_DTO());
        assertNotNull(responseInterviewFeedbackDto);
        verify(candidateDAO, times(1)).findById(ID);
        verify(employeeDAO, times(1)).findById(ID);
        verify(interviewFeedbackDAO, times(1)).save(interviewFeedbackEntity);
    }

    @Test
    void save_ShouldThrowException_WhenInterviewFeedback_IsNull() {
        when(interviewFeedbackDAO.save(null)).thenReturn(null);
        assertThrows(Exception.class, () -> interviewFeedbackService.save(null));
    }

    @Test
    void findById_ShouldFindInterviewFeedback_WhenGivenExistingId() {
        when(interviewFeedbackDAO.findById(ArgumentMatchers.eq(IFB_ID))).thenReturn(createOptional());
        ResponseInterviewFeedbackDto resp = interviewFeedbackService.getById(IFB_ID);
        assertNotNull(resp);
        assertEquals(IFB_ID, resp.getId());
        verify(interviewFeedbackDAO, times(1)).findById(ArgumentMatchers.eq(IFB_ID));
    }

    @Test
    void findById_ShouldThrowException_WhenGivenNotExistingId() {
        when(interviewFeedbackDAO.findById(ArgumentMatchers.eq(IFB_ID))).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> interviewFeedbackService.getById(IFB_ID));
        verify(interviewFeedbackDAO, times(1)).findById(ArgumentMatchers.eq(IFB_ID));
    }

    @Test
    void findAll_ShouldFindAll_WhenInterviewFeedback_ExistingInDb() {
        when(interviewFeedbackDAO.findAll()).thenReturn(createIFB_List());
        List<ResponseInterviewFeedbackDto> dtoList = interviewFeedbackService.getAll();
        assertNotNull(dtoList);
        assertEquals(createResponseIFB_DTO_List(), dtoList);
        verify(interviewFeedbackDAO, times(1)).findAll();
    }

    @Test
    void findAll_ShouldThrowException_WhenInterviewFeedback_NotExistingInDb() {
        when(interviewFeedbackDAO.findAll()).thenReturn(createEmptyIFB_List());
        assertThrows(ResponseStatusException.class, () -> interviewFeedbackService.getAll());
    }

    @Test
    void deleteById_ShouldDeleteInterviewFeedback_ById_WhenGivenExistingId() {
        when(interviewFeedbackDAO.existsById(IFB_ID)).thenReturn(true);
        interviewFeedbackService.delete(IFB_ID);
        verify(interviewFeedbackDAO, times(1)).deleteById(IFB_ID);
    }

    @Test
    void deleteById_ShouldThrowException_WhenGivenNotExistingId() {
        when(interviewFeedbackDAO.existsById(IFB_ID)).thenReturn(false);
        assertThrows(NoSuchException.class, () -> interviewFeedbackService.delete(IFB_ID));
    }

    private List<InterviewFeedbackEntity> createIFB_List() {
        InterviewFeedbackEntity interviewFeedbackEntity = new InterviewFeedbackEntity();
        interviewFeedbackEntity.setId(IFB_ID);
        List<InterviewFeedbackEntity> list = new ArrayList<>();
        list.add(interviewFeedbackEntity);
        return list;
    }

    private List<InterviewFeedbackEntity> createEmptyIFB_List() {
        return new ArrayList<>();
    }

    private List<ResponseInterviewFeedbackDto> createResponseIFB_DTO_List() {
        ResponseInterviewFeedbackDto responseInterviewFeedbackDto = new ResponseInterviewFeedbackDto();
        responseInterviewFeedbackDto.setId(IFB_ID);
        List<ResponseInterviewFeedbackDto> list = new ArrayList<>();
        list.add(responseInterviewFeedbackDto);
        return list;
    }

    private CreateInterviewFeedbackDto createIFB_DTO() {
        CreateInterviewFeedbackDto createInterviewFeedbackDto = new CreateInterviewFeedbackDto();
        createInterviewFeedbackDto.setIdCandidate(ID);
        createInterviewFeedbackDto.setIdEmployee(ID);
        return createInterviewFeedbackDto;
    }

    private Optional<InterviewFeedbackEntity> createOptional() {
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setId(ID);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(ID);
        InterviewFeedbackEntity entity = new InterviewFeedbackEntity();
        entity.setId(IFB_ID);
        entity.setEmployee(employeeEntity);
        entity.setCandidate(candidateEntity);
        return Optional.of(entity);
    }
}
package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.BaseTestClass;
import com.exadel.team2.sandbox.dao.InterviewFeedbackDAO;
import com.exadel.team2.sandbox.entity.InterviewFeedbackEntity;
import com.exadel.team2.sandbox.service.InterviewFeedbackService;
import com.exadel.team2.sandbox.web.interview_feedback.ResponseInterviewFeedbackDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InterviewFeedbackServiceImplTest extends BaseTestClass {
    private static final long ID = 1L;
    @MockBean
    private InterviewFeedbackDAO dao;
    @Autowired
    private InterviewFeedbackService service;

    @Test
    void getByIdExist() {
        when(dao.findById(ID)).thenReturn(getInterwievFeedbackEntity());
        ResponseInterviewFeedbackDto actualInterwievFeedback = service.getById(ID);
        Assertions.assertEquals(ID, actualInterwievFeedback.getId());
    }

    @Test
    void getById_noInterviewFeedback_exceptionThrown() {
        when(dao.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.getById(ID));
    }

    @Test
    void getAllInterviewExistInDB() {
        List<InterviewFeedbackEntity> interviewFeedbackEntities = createInterviewFeedbackEntity();
        when(dao.findAll()).thenReturn(interviewFeedbackEntities);
        List<ResponseInterviewFeedbackDto> actual = service.getAll();
        List<ResponseInterviewFeedbackDto> expected = createInterviewFeedbackDto();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_IFB_DoesNotExist_exceptionThrown() {
        List<InterviewFeedbackEntity> emptyEntity = createEmptyEntity();
        when(dao.findAll()).thenReturn(emptyEntity);
        assertThrows(ResponseStatusException.class, () -> service.getAll());
    }

    @Test
    void deleteById_IFB_Exists() {
        when(dao.existsById(ID)).thenReturn(true);
        boolean status = service.delete(ID);
        verify(dao).deleteById(ID);
        Assertions.assertTrue(status);
    }

    private List<InterviewFeedbackEntity> createInterviewFeedbackEntity() {
        InterviewFeedbackEntity entityFirst = new InterviewFeedbackEntity();
        entityFirst.setId(1L);
        InterviewFeedbackEntity entitySecond = new InterviewFeedbackEntity();
        entitySecond.setId(2L);
        InterviewFeedbackEntity entityThird = new InterviewFeedbackEntity();
        entityThird.setId(3L);
        List<InterviewFeedbackEntity> interviewFeedbackEntities = new ArrayList<>();
        interviewFeedbackEntities.add(entityFirst);
        interviewFeedbackEntities.add(entitySecond);
        interviewFeedbackEntities.add(entityThird);
        return interviewFeedbackEntities;
    }

    private List<ResponseInterviewFeedbackDto> createInterviewFeedbackDto() {
        ResponseInterviewFeedbackDto responseInterviewFeedbackDto1 = new ResponseInterviewFeedbackDto();
        responseInterviewFeedbackDto1.setId(1L);
        ResponseInterviewFeedbackDto responseInterviewFeedbackDto2 = new ResponseInterviewFeedbackDto();
        responseInterviewFeedbackDto2.setId(2L);
        ResponseInterviewFeedbackDto responseInterviewFeedbackDto3 = new ResponseInterviewFeedbackDto();
        responseInterviewFeedbackDto3.setId(3L);
        List<ResponseInterviewFeedbackDto> responseInterviewFeedbackDtos = new ArrayList<>();
        responseInterviewFeedbackDtos.add(responseInterviewFeedbackDto1);
        responseInterviewFeedbackDtos.add(responseInterviewFeedbackDto2);
        responseInterviewFeedbackDtos.add(responseInterviewFeedbackDto3);
        return responseInterviewFeedbackDtos;
    }

    private List<InterviewFeedbackEntity> createEmptyEntity() {
        List<InterviewFeedbackEntity> entities = new ArrayList<>();
        return entities;
    }

    private Optional<InterviewFeedbackEntity> getInterwievFeedbackEntity() {
        InterviewFeedbackEntity entity = new InterviewFeedbackEntity();
        entity.setId(ID);
        return Optional.of(entity);
    }
}
package com.exadel.team2.sandbox.service.impl;


import com.exadel.team2.sandbox.dao.InterviewFeedbackDAO;
import com.exadel.team2.sandbox.entity.InterviewFeedbackEntity;
import com.exadel.team2.sandbox.service.InterviewFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
@RequiredArgsConstructor
public class InterviewFeedbackServiceImpl implements InterviewFeedbackService {

    private final InterviewFeedbackDAO dao;

    @Override
    public InterviewFeedbackEntity getById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<InterviewFeedbackEntity> getAll() {
        return dao.findAll();
    }

    @Override
    public InterviewFeedbackEntity save(InterviewFeedbackEntity interviewFeedbackEntity) {
        return dao.save(interviewFeedbackEntity);
    }

    @Override
    public InterviewFeedbackEntity update(InterviewFeedbackEntity interviewFeedbackEntity) {
        return dao.save(interviewFeedbackEntity);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}

package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.InterviewFeedbackEntity;

import java.util.List;

public interface InterviewFeedbackService {
    InterviewFeedbackEntity getById(Long id);

    List<InterviewFeedbackEntity> getAll();

    InterviewFeedbackEntity save(InterviewFeedbackEntity interviewFeedbackEntity);

    InterviewFeedbackEntity update(InterviewFeedbackEntity interviewFeedbackEntity);

    void delete(Long id);
}

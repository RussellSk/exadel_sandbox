package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.InterviewTimeEntity;

import java.util.List;

public interface InterviewTimeServer {

    InterviewTimeEntity findById(Long id);

    List<InterviewTimeEntity> getAll();

    InterviewTimeEntity save(InterviewTimeEntity interviewTimeEntity);

    InterviewTimeEntity update(InterviewTimeEntity interviewTimeEntity);

    void delete(Long id);
}

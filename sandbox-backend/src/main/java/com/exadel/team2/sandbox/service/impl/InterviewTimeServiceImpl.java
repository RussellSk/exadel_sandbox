package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.InterviewTimeDAO;
import com.exadel.team2.sandbox.entity.InterviewTimeEntity;
import com.exadel.team2.sandbox.service.InterviewTimeServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InterviewTimeServiceImpl implements InterviewTimeServer {

    private final InterviewTimeDAO interviewTimeDAO;

    @Override
    public InterviewTimeEntity findById(Long id) {
        return interviewTimeDAO.findById(id).orElse(null);
    }

    @Override
    public List<InterviewTimeEntity> getAll() {
        return interviewTimeDAO.findAll();
    }

    @Override
    public InterviewTimeEntity save(InterviewTimeEntity interviewTimeEntity) {
        return interviewTimeDAO.save(interviewTimeEntity);
    }

    @Override
    public InterviewTimeEntity update(InterviewTimeEntity interviewTimeEntity) {
        return interviewTimeDAO.save(interviewTimeEntity);
    }

    @Override
    public void delete(Long id) {
        interviewTimeDAO.deleteById(id);
    }
}

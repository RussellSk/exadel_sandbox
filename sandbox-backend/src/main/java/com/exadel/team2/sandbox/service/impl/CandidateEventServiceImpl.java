package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.CandidateEventDAO;
import com.exadel.team2.sandbox.entity.CandidateEventEntity;
import com.exadel.team2.sandbox.service.CandidateEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CandidateEventServiceImpl implements CandidateEventService {
    private final CandidateEventDAO dao;

    @Override
    public CandidateEventEntity getById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<CandidateEventEntity> getAll() {
        return dao.findAll();
    }

    @Override
    public CandidateEventEntity save(CandidateEventEntity candidateEventEntity) {
        return dao.save(candidateEventEntity);
    }

    @Override
    public CandidateEventEntity update(CandidateEventEntity candidateEventEntity) {
        return dao.save(candidateEventEntity);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}

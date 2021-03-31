package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.CandidateEventEntity;

import java.util.List;

public interface CandidateEventService {
    CandidateEventEntity getById(Long id);

    List<CandidateEventEntity> getAll();

    CandidateEventEntity save(CandidateEventEntity candidateEventEntity);

    CandidateEventEntity update(CandidateEventEntity candidateEventEntity);

    void delete(Long id);
}

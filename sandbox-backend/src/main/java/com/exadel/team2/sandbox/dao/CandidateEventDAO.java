package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.CandidateEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CandidateEventDAO extends JpaRepository<CandidateEventEntity, Long> {
}

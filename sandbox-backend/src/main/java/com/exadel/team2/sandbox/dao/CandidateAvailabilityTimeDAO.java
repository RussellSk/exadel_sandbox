package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.CandidateAvailabilityTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateAvailabilityTimeDAO extends JpaRepository<CandidateAvailabilityTimeEntity, Long> {
    List<CandidateAvailabilityTimeEntity> findAllByCandidateId(Long candidateId);
    void deleteAllByCandidateId(Long candidateId);
}

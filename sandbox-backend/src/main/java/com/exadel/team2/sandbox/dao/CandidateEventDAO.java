package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.CandidateEventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CandidateEventDAO extends JpaRepository<CandidateEventEntity, Long>, JpaSpecificationExecutor<CandidateEventEntity> {
    Page<CandidateEventEntity> findAll(Specification<CandidateEventEntity> specification, Pageable pageable);

}

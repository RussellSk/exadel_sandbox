package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.InterviewFeedbackEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InterviewFeedbackDAO extends JpaRepository<InterviewFeedbackEntity, Long>,
        JpaSpecificationExecutor<InterviewFeedbackEntity> {
    Page<InterviewFeedbackEntity> findAll(Specification<InterviewFeedbackEntity> specification, Pageable pageable);
}

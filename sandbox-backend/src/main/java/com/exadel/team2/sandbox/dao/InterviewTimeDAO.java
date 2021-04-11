package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.InterviewTimeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewTimeDAO extends JpaRepository<InterviewTimeEntity, Long> {
    @Override
    Page<InterviewTimeEntity> findAll(Pageable pageable);
}

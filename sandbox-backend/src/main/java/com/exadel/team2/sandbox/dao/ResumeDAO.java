package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.ResumeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeDAO extends JpaRepository<ResumeEntity, Long> {
    @Override
    Page<ResumeEntity> findAll(Pageable pageable);
}

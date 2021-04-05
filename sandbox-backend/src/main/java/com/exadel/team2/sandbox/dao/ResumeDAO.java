package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeDAO extends JpaRepository<ResumeEntity, Long> {
}

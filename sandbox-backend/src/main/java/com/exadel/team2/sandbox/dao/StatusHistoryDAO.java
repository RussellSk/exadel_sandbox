package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusHistoryDAO extends JpaRepository<StatusHistory,Long> {
}

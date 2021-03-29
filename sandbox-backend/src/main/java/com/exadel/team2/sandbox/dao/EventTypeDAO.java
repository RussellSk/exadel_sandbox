package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.EventTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTypeDAO extends JpaRepository<EventTypeEntity, Long> {
}

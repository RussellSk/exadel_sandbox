package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDAO extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {
    Page<EventEntity> findAll(Specification<EventEntity> specification, Pageable pageable);


}

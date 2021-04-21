package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDAO extends JpaRepository<Status, Long> {
    Page<Status> findAll(Specification<Status> specification, Pageable pageable);
}

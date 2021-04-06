package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<RoleEntity, Long> {
    Page<RoleEntity> findAll(Pageable pageable);
}

package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDAO extends GeneralDAO<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByEmail(String email);

    Page<EmployeeEntity> findAll(Specification<EmployeeEntity> specification, Pageable pageable);
}

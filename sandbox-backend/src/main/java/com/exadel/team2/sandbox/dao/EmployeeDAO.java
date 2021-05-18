package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeDAO extends GeneralDAO<EmployeeEntity, Long> {
    EmployeeEntity findByEmail(String email);
}

package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.BaseEntity;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GeneralDAO<E extends BaseEntity, ID extends Long>
        extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
    Page<E> findAll(Specification<E> specification, Pageable pageable);
}

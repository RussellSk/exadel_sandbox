package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeEntity getById(Long id);
    List<EmployeeEntity> getAll();
    EmployeeEntity save(EmployeeEntity employeeEntity);
    EmployeeEntity update(EmployeeEntity employeeEntity);
    void delete(Long id);
}

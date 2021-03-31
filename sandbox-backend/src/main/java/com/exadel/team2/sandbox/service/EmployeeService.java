package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.web.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeEntity getById(Long id);
    List<EmployeeEntity> getAll();
    EmployeeEntity save(EmployeeDTO employeeDTO);
    EmployeeEntity update(Long id, EmployeeDTO employeeDTO);
    void delete(Long id);
}

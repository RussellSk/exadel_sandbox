package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.web.employee.CreateEmployeeDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.employee.UpdateEmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    ResponseEmployeeDto getById(Long id);
    List<ResponseEmployeeDto> getAll();
    Page<ResponseEmployeeDto> getAllPageable(Pageable pageable);
    ResponseEmployeeDto save(CreateEmployeeDto createEmployeeDTO);
    ResponseEmployeeDto update(Long id, UpdateEmployeeDto updateEmployeeDto);
    void delete(Long id);
}

package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.GeneralDto;
import com.exadel.team2.sandbox.web.employee.CreateEmployeeDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.employee.UpdateEmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GeneralService<R extends GeneralDto, C extends GeneralDto, U extends GeneralDto> {
    R getById(Long id);
    List<R> getAll();
    Page<R> getAllPageable(Pageable pageable, String search);
    R save(C createEmployeeDTO);
    R update(Long id, U updateEmployeeDto);
    void delete(Long id);
}

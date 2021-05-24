package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.web.employee.CreateEmployeeDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.employee.UpdateEmployeeDto;
import com.exadel.team2.sandbox.web.employee_availability_time.ResponseCrossedTimeSlots;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface EmployeeService extends GeneralService<ResponseEmployeeDto, CreateEmployeeDto, UpdateEmployeeDto> {
    ResponseCrossedTimeSlots getCandidateTime(Long employeeId, Long candidateId);
}

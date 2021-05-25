package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.employee.CreateEmployeeDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.employee.UpdateEmployeeDto;
import com.exadel.team2.sandbox.web.employee_availability_time.ResponseCrossedTimeSlots;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface EmployeeService extends UserDetailsService,
        GeneralService<ResponseEmployeeDto, CreateEmployeeDto, UpdateEmployeeDto> {
        ResponseCrossedTimeSlots getCrossedTime(Long employeeId, Long candidateId);
}

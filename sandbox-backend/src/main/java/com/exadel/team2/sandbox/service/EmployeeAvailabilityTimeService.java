package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.employee_availability_time.CreateEmployeeAvailabilityTimeDto;
import com.exadel.team2.sandbox.web.employee_availability_time.ResponseEmployeeAvailabilityTimeDto;
import com.exadel.team2.sandbox.web.employee_availability_time.UpdateEmployeeAvailabilityTimeDto;

public interface EmployeeAvailabilityTimeService extends GeneralService<ResponseEmployeeAvailabilityTimeDto,
        CreateEmployeeAvailabilityTimeDto,
        UpdateEmployeeAvailabilityTimeDto> {

    ResponseEmployeeAvailabilityTimeDto getByEmployeeId(Long id);
    void deleteAllByEmployeeId(Long id);
}

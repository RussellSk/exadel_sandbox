package com.exadel.team2.sandbox.dao;

import com.exadel.team2.sandbox.entity.EmployeeAvailabilityEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeAvailabilityTimeDAO extends GeneralDAO<EmployeeAvailabilityEntity, Long> {
    List<EmployeeAvailabilityEntity> findAllByEmployeeId(Long employeeId);
    void deleteAllByEmployeeId(Long employeeId);
}

package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EmployeeAvailabilityTimeDAO;
import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.entity.EmployeeAvailabilityEntity;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.mapper.EmployeeAvailabilityTimeMapper;
import com.exadel.team2.sandbox.service.EmployeeAvailabilityTimeService;
import com.exadel.team2.sandbox.web.employee_availability_time.CreateEmployeeAvailabilityTimeDto;
import com.exadel.team2.sandbox.web.employee_availability_time.ResponseEmployeeAvailabilityTimeDto;
import com.exadel.team2.sandbox.web.employee_availability_time.TimeId;
import com.exadel.team2.sandbox.web.employee_availability_time.UpdateEmployeeAvailabilityTimeDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class EmployeeAvailabilityTimeServiceImpl extends GeneralServiceImpl<EmployeeAvailabilityEntity,
        ResponseEmployeeAvailabilityTimeDto, CreateEmployeeAvailabilityTimeDto,
        UpdateEmployeeAvailabilityTimeDto> implements EmployeeAvailabilityTimeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeAvailabilityTimeServiceImpl(EmployeeAvailabilityTimeDAO employeeAvailabilityTimeDAO,
                                               EmployeeDAO employeeDAO,
                                               EmployeeAvailabilityTimeMapper employeeAvailabilityTimeMapper) {
        this.employeeDAO = employeeDAO;
        this.generalDAO = employeeAvailabilityTimeDAO;
        this.generalMapper = employeeAvailabilityTimeMapper;
    }

    public ResponseEmployeeAvailabilityTimeDto getByEmployeeId(Long id) {
        List<EmployeeAvailabilityEntity> availabilityEntity = ((EmployeeAvailabilityTimeDAO)generalDAO).findAllByEmployeeId(id);
        ResponseEmployeeAvailabilityTimeDto responseDto = new ResponseEmployeeAvailabilityTimeDto();
        if (availabilityEntity == null) {
            return responseDto;
        }

        responseDto.setEmployeeId(id);
        availabilityEntity.forEach(slot -> responseDto.getAvailableTimeSlots().add(new TimeId(slot.getId(), slot.getDateTime())));

        return responseDto;
    }

    @Override
    public ResponseEmployeeAvailabilityTimeDto save(CreateEmployeeAvailabilityTimeDto createDTO) {
        EmployeeEntity employeeEntity = employeeDAO.findById(createDTO.getEmployeeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        ResponseEmployeeAvailabilityTimeDto responseDto = new ResponseEmployeeAvailabilityTimeDto();
        responseDto.setEmployeeId(createDTO.getEmployeeId());

        createDTO.getAvailableTimeSlots().forEach(dateTime -> {
            EmployeeAvailabilityEntity availabilityEntity = new EmployeeAvailabilityEntity();
            availabilityEntity.setId(null);
            availabilityEntity.setEmployee(employeeEntity);
            availabilityEntity.setDateTime(dateTime);
            availabilityEntity.setCreatedAt(LocalDateTime.now());
            availabilityEntity.setUpdatedAt(LocalDateTime.now());
            generalDAO.save(availabilityEntity);

            responseDto.getAvailableTimeSlots().add(new TimeId(availabilityEntity.getId(), dateTime));
        });

        return responseDto;
    }

    public void deleteAllByEmployeeId(Long employeeId) {
        ((EmployeeAvailabilityTimeDAO)generalDAO).deleteAllByEmployeeId(employeeId);
    }
}

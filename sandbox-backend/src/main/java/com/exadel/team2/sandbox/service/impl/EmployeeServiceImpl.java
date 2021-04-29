package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.dao.RoleDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.mapper.EmployeeMapper;
import com.exadel.team2.sandbox.service.EmployeeService;
import com.exadel.team2.sandbox.web.employee.CreateEmployeeDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.employee.UpdateEmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class EmployeeServiceImpl extends GeneralServiceImpl<EmployeeEntity,
        ResponseEmployeeDto, CreateEmployeeDto, UpdateEmployeeDto> implements EmployeeService {

    private final RoleDAO roleDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO, RoleDAO roleDAO, EmployeeMapper employeeMapper) {
        this.generalDAO = employeeDAO;
        this.roleDAO = roleDAO;
        this.generalMapper = employeeMapper;
    }

    @Override
    public ResponseEmployeeDto save(CreateEmployeeDto createEmployeeDTO) {

        EmployeeEntity employeeEntity = generalMapper.convertDtoToEntity(createEmployeeDTO);
        RoleEntity roleEntity = roleDAO.findById(createEmployeeDTO.getRoleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));

        employeeEntity.setRole(roleEntity);
        employeeEntity.setCreatedAt(LocalDateTime.now());
        employeeEntity.setUpdatedAt(LocalDateTime.now());
        generalDAO.save(employeeEntity);
        return generalMapper.convertEntityToDto(employeeEntity);
    }


    @Override
    public ResponseEmployeeDto update(Long id, UpdateEmployeeDto updateEmployeeDto) {
        EmployeeEntity employeeEntity = generalDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));

        if (updateEmployeeDto.getRoleId() != null) {
            RoleEntity roleEntity = roleDAO.findById(updateEmployeeDto.getRoleId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));
            employeeEntity.setRole(roleEntity);
        }

        if (updateEmployeeDto.getLastName() != null) {
            employeeEntity.setLastName(updateEmployeeDto.getLastName());
        }

        if (updateEmployeeDto.getFirstName() != null) {
            employeeEntity.setFirstName(updateEmployeeDto.getFirstName());
        }

        if (updateEmployeeDto.getPrimaryTechnology() != null) {
            employeeEntity.setPrimaryTechnology(updateEmployeeDto.getPrimaryTechnology());
        }

        if (updateEmployeeDto.getType() != null) {
            employeeEntity.setType(updateEmployeeDto.getType());
        }

        if (updateEmployeeDto.getPhone() != null) {
            employeeEntity.setPhone(updateEmployeeDto.getPhone());
        }

        if (updateEmployeeDto.getEmail() != null) {
            employeeEntity.setEmail(updateEmployeeDto.getEmail());
        }

        if (updateEmployeeDto.getSkype() != null) {
            employeeEntity.setSkype(updateEmployeeDto.getSkype());
        }

        if (updateEmployeeDto.getLocationCountry() != null) {
            employeeEntity.setLocationCity(updateEmployeeDto.getLocationCity());
        }

        if (updateEmployeeDto.getLocationCountry() != null) {
            employeeEntity.setLocationCountry(updateEmployeeDto.getLocationCountry());
        }

        if (updateEmployeeDto.getTimezone() != null) {
            employeeEntity.setTimezone(updateEmployeeDto.getTimezone());
        }

        employeeEntity.setUpdatedAt(LocalDateTime.now());
        return generalMapper.convertEntityToDto(generalDAO.save(employeeEntity));
    }

    @Override
    public void delete(Long id) {
        generalDAO.deleteById(id);
    }
}

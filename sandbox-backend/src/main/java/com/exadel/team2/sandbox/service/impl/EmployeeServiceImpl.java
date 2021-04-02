package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.service.EmployeeService;
import com.exadel.team2.sandbox.service.RoleService;
import com.exadel.team2.sandbox.web.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final RoleService roleService;

    @Override
    public EmployeeEntity getById(Long id) {
        return employeeDAO.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Page<EmployeeEntity> getAllPageable(Pageable pageable) {
        return employeeDAO.findAll(pageable);
    }

    @Override
    public EmployeeEntity save(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmpFirstName(employeeDTO.getEmpFirstName());
        employeeEntity.setEmpLastName(employeeDTO.getEmpLastName());

        RoleEntity roleEntity = roleService.getById(employeeDTO.getRoleId());
        if (roleEntity != null) {
            employeeEntity.setRole(roleEntity);
        }

        employeeEntity.setEmpCreatedAt(LocalDateTime.now());
        employeeEntity.setEmpUpdatedAt(LocalDateTime.now());

        return employeeDAO.save(employeeEntity);
    }

    @Override
    public EmployeeEntity update(Long id, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = getById(id);
        employeeEntity.setEmpFirstName(employeeDTO.getEmpFirstName());
        employeeEntity.setEmpLastName(employeeDTO.getEmpLastName());
        RoleEntity roleEntity = roleService.getById(employeeDTO.getRoleId());
        employeeEntity.setRole(roleEntity);
        employeeEntity.setEmpUpdatedAt(LocalDateTime.now());
        return employeeDAO.save(employeeEntity);
    }

    @Override
    public void delete(Long id) {
        employeeDAO.deleteById(id);
    }
}

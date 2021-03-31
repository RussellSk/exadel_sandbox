package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Override
    public EmployeeEntity getById(Long id) {
        return employeeDAO.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return employeeDAO.findAll();
    }

    @Override
    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        return employeeDAO.save(employeeEntity);
    }

    @Override
    public EmployeeEntity update(EmployeeEntity employeeEntity) {
        return employeeDAO.save(employeeEntity);
    }

    @Override
    public void delete(Long id) {
        employeeDAO.deleteById(id);
    }
}

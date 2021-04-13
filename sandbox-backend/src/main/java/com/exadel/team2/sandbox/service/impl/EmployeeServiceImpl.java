package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.dao.RoleDAO;
import com.exadel.team2.sandbox.dao.rsql.CustomRsqlVisitor;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.mapper.EmployeeMapper;
import com.exadel.team2.sandbox.service.EmployeeService;
import com.exadel.team2.sandbox.web.employee.CreateEmployeeDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.employee.UpdateEmployeeDto;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final RoleDAO roleDAO;
    private final EmployeeMapper employeeMapper;

    @Override
    public ResponseEmployeeDto getById(Long id) {
        EmployeeEntity employeeEntity = employeeDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));
        return employeeMapper.convertEntityToDto(employeeEntity);
    }

    @Override
    public List<ResponseEmployeeDto> getAll() {
        List<EmployeeEntity> employeeEntities = employeeDAO.findAll();
        if (employeeEntities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Content");
        }

        return employeeEntities.stream()
                .map(employeeMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ResponseEmployeeDto> getAllPageable(Pageable pageable, String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<EmployeeEntity> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return employeeDAO.findAll(spec, pageable)
                .map(employeeMapper::convertEntityToDto);
    }

    @Override
    public ResponseEmployeeDto save(CreateEmployeeDto createEmployeeDTO) {
        EmployeeEntity employeeEntity = employeeMapper.convertDtoToEntity(createEmployeeDTO);
        RoleEntity roleEntity = roleDAO.findById(createEmployeeDTO.getRoleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));

        employeeEntity.setRole(roleEntity);
        employeeEntity.setEmpCreatedAt(LocalDateTime.now());
        employeeEntity.setEmpUpdatedAt(LocalDateTime.now());

        employeeDAO.save(employeeEntity);

        return employeeMapper.convertEntityToDto(employeeEntity);
    }

    @Override
    public ResponseEmployeeDto update(Long id, UpdateEmployeeDto updateEmployeeDto) {
        EmployeeEntity employeeEntity = employeeDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));

        if (updateEmployeeDto.getRoleId() != null) {
            RoleEntity roleEntity = roleDAO.findById(updateEmployeeDto.getRoleId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));
            employeeEntity.setRole(roleEntity);
        }

        if (updateEmployeeDto.getEmpLastName() != null) {
            employeeEntity.setEmpLastName(updateEmployeeDto.getEmpLastName());
        }

        if (updateEmployeeDto.getEmpFirstName() != null) {
            employeeEntity.setEmpFirstName(updateEmployeeDto.getEmpFirstName());
        }

        if (updateEmployeeDto.getEmpPhone() != null) {
            employeeEntity.setEmpPhone(updateEmployeeDto.getEmpPhone());
        }

        if (updateEmployeeDto.getEmpEmail() != null) {
            employeeEntity.setEmpEmail(updateEmployeeDto.getEmpEmail());
        }

        if (updateEmployeeDto.getEmpSkype() != null) {
            employeeEntity.setEmpSkype(updateEmployeeDto.getEmpSkype());
        }

        employeeEntity.setEmpUpdatedAt(LocalDateTime.now());

        return employeeMapper.convertEntityToDto(employeeDAO.save(employeeEntity));
    }

    @Override
    public void delete(Long id) {
        employeeDAO.deleteById(id);
    }
}

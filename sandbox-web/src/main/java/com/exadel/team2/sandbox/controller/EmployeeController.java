package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.web.EmployeeDTO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.service.EmployeeService;
import com.exadel.team2.sandbox.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
        EmployeeEntity employeeEntity = employeeService.getById(id);
        if (employeeEntity == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(employeeEntity);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmpFirstName(employeeDTO.getEmpFirstName());
        employeeEntity.setEmpLastName(employeeDTO.getEmpLastName());

        RoleEntity roleEntity = roleService.getById(employeeDTO.getRoleId());
        if (roleEntity != null) {
            employeeEntity.setRole(roleEntity);
        }

        employeeEntity.setEmpCreatedAt(LocalDateTime.now());
        employeeEntity.setEmpUpdatedAt(LocalDateTime.now());
        employeeEntity = employeeService.save(employeeEntity);

        return ResponseEntity.ok(employeeEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = employeeService.getById(id);
        if (employeeEntity == null) {
            return ResponseEntity.notFound().build();
        }

        employeeEntity.setEmpFirstName(employeeDTO.getEmpFirstName());
        employeeEntity.setEmpLastName(employeeDTO.getEmpLastName());

        RoleEntity roleEntity = roleService.getById(employeeDTO.getRoleId());
        employeeEntity.setRole(roleEntity);

        employeeEntity.setEmpUpdatedAt(LocalDateTime.now());
        employeeEntity = employeeService.update(employeeEntity);

        return ResponseEntity.ok(employeeEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        if (employeeService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}

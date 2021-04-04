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

    @GetMapping("/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @GetMapping
    public List<EmployeeEntity> getEmployees() {
        return employeeService.getAll();
    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.save(employeeDTO);
    }

    @PutMapping("/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.update(id, employeeDTO);
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

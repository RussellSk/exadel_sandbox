package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.EmployeeService;
import com.exadel.team2.sandbox.web.employee.CreateEmployeeDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.employee.UpdateEmployeeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @GetMapping
    public Page<ResponseEmployeeDto> getEmployeesRsql(
            @RequestParam(value = "search") String search,
            @RequestParam(defaultValue = "0", name = "page") Integer page,
            @RequestParam(defaultValue = "15", name = "itemsPerPage") Integer itemsPerPage) {
        return employeeService.getAllPageable(PageRequest.of(page, itemsPerPage), search);
    }

    @PostMapping
    public ResponseEmployeeDto createEmployee(@Validated @RequestBody CreateEmployeeDto createEmployeeDTO) {
        return employeeService.save(createEmployeeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEmployeeDto updateEmployee(@PathVariable Long id, @Validated @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        return employeeService.update(id, updateEmployeeDto);
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

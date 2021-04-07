package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.dao.rsql.CustomRsqlVisitor;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.service.EmployeeService;
import com.exadel.team2.sandbox.web.employee.CreateEmployeeDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.employee.UpdateEmployeeDto;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            Node rootNode = new RSQLParser().parse(search);
            Specification<EmployeeEntity> spec = rootNode.accept(new CustomRsqlVisitor<>());
            return employeeService.getAllPageable(PageRequest.of(page, itemsPerPage), spec);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
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

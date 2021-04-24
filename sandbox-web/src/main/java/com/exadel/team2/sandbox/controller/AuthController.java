package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.service.EmployeeService;
import com.exadel.team2.sandbox.web.auth.RequestAuthDto;
import com.exadel.team2.sandbox.web.auth.ResponseAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final EmployeeService employeeService;

    @PostMapping("/auth")
    public ResponseAuthDto auth(@RequestBody RequestAuthDto request) {
        return new ResponseAuthDto();
    }
}

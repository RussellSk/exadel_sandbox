package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.AuthService;
import com.exadel.team2.sandbox.web.auth.RequestAuthDto;
import com.exadel.team2.sandbox.web.auth.ResponseAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseAuthDto auth(@RequestBody @Validated RequestAuthDto request) {
        return authService.authenticateEmployee(request);
    }
}

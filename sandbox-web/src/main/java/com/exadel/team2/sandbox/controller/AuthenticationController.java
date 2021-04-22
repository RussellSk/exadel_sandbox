package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.AuthenticationService;
import com.exadel.team2.sandbox.web.authentication.AuthenticationDto;
import com.exadel.team2.sandbox.web.authentication.ResponseAuthenticationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseAuthenticationDto login(@RequestBody AuthenticationDto authenticationDto) throws Exception {
        return authenticationService.login(authenticationDto);
    }
}

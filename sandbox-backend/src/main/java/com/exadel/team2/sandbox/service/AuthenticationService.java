package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.authentication.AuthenticationDto;
import com.exadel.team2.sandbox.web.authentication.ResponseAuthenticationDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;

public interface AuthenticationService {
    ResponseAuthenticationDto login(AuthenticationDto authenticationDto) throws Exception;
}

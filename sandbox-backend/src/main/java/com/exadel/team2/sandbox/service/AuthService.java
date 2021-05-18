package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.auth.RequestAuthDto;
import com.exadel.team2.sandbox.web.auth.ResponseAuthDto;

public interface AuthService {
    ResponseAuthDto authenticateEmployee(RequestAuthDto requestAuthDto);
}

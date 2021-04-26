package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.configuration.security.JwtTokenUtil;
import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.service.AuthService;
import com.exadel.team2.sandbox.service.EmployeeService;
import com.exadel.team2.sandbox.web.auth.RequestAuthDto;
import com.exadel.team2.sandbox.web.auth.ResponseAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final EmployeeDAO employeeDAO;

    @Override
    public ResponseAuthDto authenticateEmployee(RequestAuthDto requestAuthDto) throws BadCredentialsException {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(requestAuthDto.getEmail(), requestAuthDto.getPassword()));

        String email = ((User) authentication.getPrincipal()).getUsername();
        EmployeeEntity employeeEntity = employeeDAO.findByEmail(email);
        if (email == null) {
            throw new BadCredentialsException("Incorrect login");
        }
        ResponseAuthDto responseAuthDto = new ResponseAuthDto();
        responseAuthDto.setToken(jwtTokenUtil.generateAccessToken(employeeEntity));
        return responseAuthDto;
    }
}

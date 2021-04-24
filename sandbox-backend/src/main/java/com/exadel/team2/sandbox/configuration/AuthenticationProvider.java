package com.exadel.team2.sandbox.configuration;

import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.service.EmployeeService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationProvider extends DaoAuthenticationProvider {

    private final EmployeeDAO employeeDAO;

    public AuthenticationProvider(EmployeeDAO employeeDAO, EmployeeService employeeService) {
        this.setUserDetailsService(employeeService);
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final EmployeeEntity employeeEntity = employeeDAO.findByEmail(authentication.getName());
        if (employeeEntity == null) {
            throw new BadCredentialsException("Invalid Username or Password");
        }
        Authentication result = super.authenticate(authentication);
        return new UsernamePasswordAuthenticationToken(employeeEntity, result.getCredentials(), result.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

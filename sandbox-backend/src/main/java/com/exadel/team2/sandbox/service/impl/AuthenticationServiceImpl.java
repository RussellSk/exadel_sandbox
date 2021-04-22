package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.security.jwt.JwtTokenUtil;
import com.exadel.team2.sandbox.security.jwt.JwtUserDetailsService;
import com.exadel.team2.sandbox.service.AuthenticationService;
import com.exadel.team2.sandbox.web.authentication.AuthenticationDto;
import com.exadel.team2.sandbox.web.authentication.ResponseAuthenticationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final EmployeeDAO employeeDAO;

    @Override
    public ResponseAuthenticationDto login(AuthenticationDto authenticationDto) throws Exception {
    //    try {
            String empEmail = authenticationDto.getEmpEmail();
            String empPassword = authenticationDto.getEmpPassword();


            authenticate(empEmail, empPassword);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(empEmail);
            final String token = jwtTokenUtil.generateToken(userDetails);
//            Map<Object, Object> response = new HashMap<>();
//            response.put("email", empEmail);
//            response.put("token", token);
            return new ResponseAuthenticationDto(empEmail, token);
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("invalid email or password!");
//        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("invalid email or password!", e);
        }
//        catch (AuthenticationException e) {
//            throw new BadCredentialsException("invalid email or password!");
//        }
    }
}

package com.exadel.team2.sandbox.security.jwt;

import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final EmployeeDAO dao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        EmployeeEntity entity = dao.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Email: " + username + " not found!"));
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(entity);
        return userDetailsImpl;
    }

}

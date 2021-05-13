package com.exadel.team2.sandbox.configuration.security;

import com.exadel.team2.sandbox.dao.PermissionDAO;
import com.exadel.team2.sandbox.entity.PermissionEntity;
import com.exadel.team2.sandbox.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final EmployeeService employeeService;
    private final PermissionDAO permissionDAO;
    private final JwtTokenFilter jwtTokenFilter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(EmployeeService employeeService,
                          JwtTokenFilter jwtTokenFilter,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          PermissionDAO permissionDAO) {
        this.employeeService = employeeService;
        this.jwtTokenFilter = jwtTokenFilter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.permissionDAO = permissionDAO;

        // Inherit security context in async function calls
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        // Disable CSRF verification
                .csrf().disable()
        // Set session management to stateless
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
        // Set permissions and endpoints
                .authorizeRequests()
        // Swagger endpoints must be publicly accessible
                .antMatchers("/v2/api-docs", "/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
        // Our public endpoints
                .antMatchers("/auth/login").permitAll().and();

        // Our private endpoints loading from DB
        List<PermissionEntity> permissionEntityList = permissionDAO.findAll();
        for (PermissionEntity permission : permissionEntityList) {
            http.authorizeRequests()
                    .antMatchers(HttpMethod.resolve(permission.getHttpMethod()), permission.getRoute())
                    .hasAuthority(permission.getName())
                    .and();
        }

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

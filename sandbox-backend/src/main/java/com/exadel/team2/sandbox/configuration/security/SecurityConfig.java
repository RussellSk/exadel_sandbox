package com.exadel.team2.sandbox.configuration.security;

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

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final EmployeeService employeeService;
    private final JwtTokenFilter jwtTokenFilter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(EmployeeService employeeService,
                          JwtTokenFilter jwtTokenFilter, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeService = employeeService;
        this.jwtTokenFilter = jwtTokenFilter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

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
                .antMatchers("/", "/v2/api-docs", "/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                // Our public endpoints
                    .antMatchers("/auth/login").permitAll()
                // Our private endpoints loading from DB
                    .antMatchers(HttpMethod.GET,"/roles").hasAuthority("admin_view")
                    .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

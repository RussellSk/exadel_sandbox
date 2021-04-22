package com.exadel.team2.sandbox.configuration;

import com.exadel.team2.sandbox.security.jwt.JwtAuthenticationEntryPoint;
import com.exadel.team2.sandbox.security.jwt.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final UserDetailsService jwtUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/event/all").permitAll()
                .antMatchers(HttpMethod.POST, "/candidate", "/resume", "/login").permitAll()
                .antMatchers("/employees/**").hasRole("SUPER_ADMIN")
                .antMatchers("/interviewtime/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/resume/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/candidate-event/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/event/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/event-type/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/image/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/interview-feedback/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/roles", "/roles/**").hasRole("SUPER_ADMIN")
                .antMatchers("/permissions/**").hasRole("SUPER_ADMIN")
                .antMatchers("/status/**").hasRole("SUPER_ADMIN")
                .antMatchers("/status/history/**").hasRole("SUPER_ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
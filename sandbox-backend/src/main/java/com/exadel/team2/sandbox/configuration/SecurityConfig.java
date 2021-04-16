package com.exadel.team2.sandbox.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/event/all", "/candidate").permitAll()
                .antMatchers("/event-type/all").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/image/all").hasRole("SUPER_ADMIN")
                .antMatchers("/employees").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/candidate-event/all").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/interviewtime").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/resume").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/interview-feedback").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/permissions").hasRole("SUPER_ADMIN")
                .antMatchers("/roles").hasRole("SUPER_ADMIN")
                .antMatchers("/status/all").hasRole("SUPER_ADMIN")
                .antMatchers("/status/history/all").hasRole("SUPER_ADMIN")
                .and().formLogin();
    }

}

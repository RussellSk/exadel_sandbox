package com.exadel.team2.sandbox.configuration;

import com.exadel.team2.sandbox.configuration.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                .antMatchers("/event/all", "/candidate").permitAll()
                .antMatchers("/event-type/all").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/image/all").hasRole("ADMIN")
                .antMatchers("/employees").hasRole("USER")
                .antMatchers("/candidate-event/all").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/interviewtime").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/resume").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/interview-feedback").hasAnyRole("SUPER_ADMIN", "ADMIN")
                .antMatchers("/permissions").hasRole("SUPER_ADMIN")
                .antMatchers("/roles").hasRole("SUPER_ADMIN")
                .antMatchers("/status/all").hasRole("SUPER_ADMIN")
                .antMatchers("/status/history/all").hasRole("SUPER_ADMIN")

                .antMatchers("/register", "/auth").permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}

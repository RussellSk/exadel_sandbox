//package com.exadel.team2.sandbox.configuration;
//
//import com.sun.xml.bind.v2.TODO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    final DataSource dataSource;
//
//    public SecurityConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
////todo                  це не зашифрований робочий варыант з бд
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//@Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource);
////                .passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("api/event/5").hasAnyRole("DEVELOPER", "ADMIN")
//                .antMatchers("/event/all").permitAll()
//                .antMatchers("/event-type").hasRole("Super-admin")
//                .and().formLogin();
//    }          //доступ к страницам - каким ролям! Працюэ але незашифровано! поки
//
//
//
//
//    // дані про юзера в коді
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
////
////        auth.inMemoryAuthentication()
////                .withUser(userBuilder.username("Toha")
////                .password("1111")
////                .roles("Developer"));
////    }
//
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.jdbcAuthentication().dataSource(dataSource);
////    }  робило наче
//
//
//
//}

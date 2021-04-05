package com.exadel.team2.sandbox.configuration;

import com.exadel.team2.sandbox.mapper.Release;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Release getRelease() {
        return new Release();
    }
}

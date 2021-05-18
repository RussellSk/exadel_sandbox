package com.exadel.team2.sandbox;

import com.exadel.team2.sandbox.configuration.FileStorageProperties;
import com.exadel.team2.sandbox.configuration.ImageStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class,
        ImageStorageProperties.class
})
public class SandboxApplication {
    public static void main(String[] args) {
        SpringApplication.run(SandboxApplication.class, args);
    }
}

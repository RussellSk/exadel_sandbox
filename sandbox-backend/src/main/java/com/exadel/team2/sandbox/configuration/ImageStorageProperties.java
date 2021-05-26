package com.exadel.team2.sandbox.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "image")
public class ImageStorageProperties {

    private String uploadDir;

}

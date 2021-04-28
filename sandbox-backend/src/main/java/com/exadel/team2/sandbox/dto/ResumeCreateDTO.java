package com.exadel.team2.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResumeCreateDTO {

    private MultipartFile file;

//    private String path;

    private String link;

    @NotNull
    private String name;

    @NotNull
    private String ext;

    @NotNull
    private Long size;

    @JsonIgnore
    private final LocalDateTime createdAt = LocalDateTime.now();
}

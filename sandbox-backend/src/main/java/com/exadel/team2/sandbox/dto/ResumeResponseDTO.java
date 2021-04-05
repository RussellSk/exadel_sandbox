package com.exadel.team2.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResumeResponseDTO {

    @NotNull
    private Long id;

    @NotNull
    private String path;

    @NotNull
    private String link;

    @NotNull
    private String name;

    @NotNull
    private String ext;

    @NotNull
    private int size;

    @NotNull
    private LocalDateTime createdAt;
}

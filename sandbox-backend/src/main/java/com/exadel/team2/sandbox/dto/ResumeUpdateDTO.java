package com.exadel.team2.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class ResumeUpdateDTO {

    @Id
    @NotNull
    private Long id;

    private String path;

    private String link;

    @NotNull
    private String name;

    @NotNull
    private String ext;

    @NotNull
    private int size;

    @JsonIgnore
    private final LocalDateTime createdAt = LocalDateTime.now();
}

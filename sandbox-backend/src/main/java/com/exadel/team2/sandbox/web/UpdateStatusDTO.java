package com.exadel.team2.sandbox.web;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateStatusDTO {

    private String name;

    private String description;

    private LocalDateTime updatedAt;
}

package com.exadel.team2.sandbox.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponseDTO {

    private Long id;
    private String path;
    private String name;
    private String ext;
    private Integer size;
    private LocalDateTime createdAt;

}

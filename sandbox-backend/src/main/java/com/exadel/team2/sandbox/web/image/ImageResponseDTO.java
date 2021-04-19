package com.exadel.team2.sandbox.web.image;

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
    private String imageName;
    private String ext;
    private Integer size;
    private String altText;
    private LocalDateTime createdAt;

}

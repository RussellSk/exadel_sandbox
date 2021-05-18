package com.exadel.team2.sandbox.web.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponseDTO {

    private Long id;
    private String imageName;
    private String ext;
    private Long size;
    private String altText;
    private LocalDateTime createdAt;

}

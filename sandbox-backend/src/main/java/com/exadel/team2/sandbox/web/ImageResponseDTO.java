package com.exadel.team2.sandbox.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponseDTO {

    private Long imgId;
    private String imgPath;
    private String imageName;
    private String imgExt;
    private Integer imgSize;
    private LocalDateTime imgCreatedAt;

}

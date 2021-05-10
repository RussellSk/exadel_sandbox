package com.exadel.team2.sandbox.web.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageUpdateDTO {

    private Long id;       //тимчасово
    private String imageName;
    private String ext;
    private Long size;
    private String altText;
}

package com.exadel.team2.sandbox.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageUpdateDTO {

    private String imgPath;
    private String imageName;
    private String imgExt;
    private Integer imgSize;
    private String imgAltText;
}

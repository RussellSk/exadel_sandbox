package com.exadel.team2.sandbox.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageCreateDTO {

    @NotNull
    private String imgPath;
    private String imageName;
    private String imgExt;
    private Integer imgSize;
    private String imgAltText;

}

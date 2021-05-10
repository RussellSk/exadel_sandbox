package com.exadel.team2.sandbox.web.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageCreateDTO {

//    @NotNull
//    private String path;
//    private String imageName;   //ці поля автомиатично вкажуться - вручно неправильно
//    private String ext;
//    private Integer size;
    private String altText;

}

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
    private String path;
    private String name;
    private String ext;
    private Integer size;

}

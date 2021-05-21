package com.exadel.team2.sandbox.web.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UploadImageResponseDTO {

    private String name;
    private String ext;
    private Long size;

}

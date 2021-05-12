package com.exadel.team2.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UploadFileResponseDTO {

    private String fileName;
    private String fileExtension;
    private Long size;

}

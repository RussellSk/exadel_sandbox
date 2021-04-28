package com.exadel.team2.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFileResponse {

    private String fileName;
    private String fileDownloadingUri;
    private String fileType;
    private Long size;

}

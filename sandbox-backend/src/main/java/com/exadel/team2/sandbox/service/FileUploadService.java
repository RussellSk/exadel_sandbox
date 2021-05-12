package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.dto.UploadFileResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

public interface FileUploadService {

    UploadFileResponseDTO uploadFile(CandidateResponseDTO candidateResponseDTO, MultipartFile file);

    Resource loadAsResource(String filename);

    Path load(String filename);

}

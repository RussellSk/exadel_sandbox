package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.event.EventResponseDTO;
import com.exadel.team2.sandbox.web.image.UploadImageResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface ImageUploadService {

    UploadImageResponseDTO uploadImage(EventResponseDTO eventResponseDTO, MultipartFile file);

    Resource loadAsResource(String filename);

    Path load(String filename);

}

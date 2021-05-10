package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.image.ImageCreateDTO;
import com.exadel.team2.sandbox.web.image.ImageResponseDTO;
import com.exadel.team2.sandbox.web.image.ImageUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    ImageResponseDTO getById (Long id);
    List<ImageResponseDTO> getAll();
    ImageResponseDTO save(Long eventId, MultipartFile file);
    ImageResponseDTO update(Long id, ImageUpdateDTO imageUpdateDTO);
    Boolean delete(Long id);

}

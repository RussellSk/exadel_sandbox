package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.image.ImageCreateDTO;
import com.exadel.team2.sandbox.web.image.ImageResponseDTO;
import com.exadel.team2.sandbox.web.image.ImageUpdateDTO;

import java.util.List;

public interface ImageService {

    ImageResponseDTO getById (Long imgId);
    List<ImageResponseDTO> getAll();
    ImageResponseDTO save(ImageCreateDTO imageCreateDTO);
    ImageResponseDTO update(Long id, ImageUpdateDTO imageUpdateDTO);
    Boolean delete(Long imgId);

}

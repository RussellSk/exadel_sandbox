package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.ImageCreateDTO;
import com.exadel.team2.sandbox.web.ImageResponseDTO;
import com.exadel.team2.sandbox.web.ImageUpdateDTO;

import java.util.List;

public interface ImageService {

    ImageResponseDTO getById (Long imgId);
    List<ImageResponseDTO> getAll();
    ImageResponseDTO save(ImageCreateDTO imageCreateDTO);
    ImageResponseDTO update(Long id, ImageUpdateDTO imageUpdateDTO);
    String delete(Long imgId);

}

package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.ImageDAO;
import com.exadel.team2.sandbox.entity.ImageEntity;
import com.exadel.team2.sandbox.mapper.ImageMapper;
import com.exadel.team2.sandbox.service.ImageService;
import com.exadel.team2.sandbox.web.ImageCreateDTO;
import com.exadel.team2.sandbox.web.ImageResponseDTO;
import com.exadel.team2.sandbox.web.ImageUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageDAO imageDAO;
    private final ImageMapper imageMapper;

    @Override
    public ImageResponseDTO getById(Long id) {
        ImageEntity imageEntity = imageDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
        return imageMapper.convertEntityToDto(imageEntity);
    }


    @Override
    public List<ImageResponseDTO> getAll() {
        List<ImageEntity> imageEntities = imageDAO.findAll();
        if (imageEntities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Sorry, however haven't content");
        }
        return imageEntities.stream()
                .map(imageMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ImageResponseDTO save(ImageCreateDTO imageCreateDTO) {
        ImageEntity imageEntity = imageMapper.convertDtoToEntity(imageCreateDTO);

        imageEntity.setImgCreatedAt(LocalDateTime.now());
        imageDAO.save(imageEntity);
        return imageMapper.convertEntityToDto(imageEntity);
    }

    @Override
    public ImageResponseDTO update(Long id, ImageUpdateDTO imageUpdateDTO) {
        ImageEntity imageEntity = imageMapper.convertDtoToEntity(imageUpdateDTO);
        imageEntity.setImgId(id);

        return imageMapper.convertEntityToDto(imageDAO.save(imageEntity));
    }


    @Override
    public void delete(Long id) {
        imageDAO.deleteById(id);
    }
}

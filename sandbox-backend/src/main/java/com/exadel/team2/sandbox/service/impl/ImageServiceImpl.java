package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EventDAO;
import com.exadel.team2.sandbox.dao.ImageDAO;
import com.exadel.team2.sandbox.mapper.ModelMap;
import com.exadel.team2.sandbox.service.ImageUploadService;
import com.exadel.team2.sandbox.web.image.ImageCreateDTO;
import com.exadel.team2.sandbox.web.image.UploadImageResponseDTO;
import com.exadel.team2.sandbox.entity.EventEntity;
import com.exadel.team2.sandbox.entity.ImageEntity;
import com.exadel.team2.sandbox.exceptions.NoSuchException;
import com.exadel.team2.sandbox.mapper.ImageMapper;
import com.exadel.team2.sandbox.service.EventService;
import com.exadel.team2.sandbox.service.ImageService;
import com.exadel.team2.sandbox.web.event.EventResponseDTO;
import com.exadel.team2.sandbox.web.event.EventUpdateDTO;
import com.exadel.team2.sandbox.web.image.ImageResponseDTO;
import com.exadel.team2.sandbox.web.image.ImageUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageDAO imageDAO;
    private final ImageMapper imageMapper;
    private final EventDAO eventDAO;
    private final EventService eventService;
    private final ImageUploadService imageUploadService;
    private final ModelMap mapper;


    @Override
    public ImageResponseDTO getById(Long id) {
        ImageEntity imageEntity = imageDAO.findById(id)
                .orElseThrow(() -> new NoSuchException("Image with ID = " + id + " not found in Database"));
        return imageMapper.convertEntityToDto(imageEntity);
    }


    @Override
    public List<ImageResponseDTO> getAll() {
        List<ImageEntity> imageEntities = imageDAO.findAll();
        if (imageEntities.isEmpty()) {
            throw new NoSuchException("Not found images in Database");
        }
        return imageEntities.stream()
                .map(imageMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ImageResponseDTO save(Long eventId, MultipartFile image) {

        EventEntity eventEntity = eventDAO.findById(eventId)
                .orElseThrow(() -> new NoSuchException("Not found event in Database"));

        EventResponseDTO eventResponseDTO = mapper.convertTo(eventEntity, EventResponseDTO.class);


        if (image != null) {

            UploadImageResponseDTO uploadImageResponseDTO = imageUploadService.uploadImage(eventResponseDTO, image);

            ImageEntity imageEntity = imageDAO.save(ImageEntity.builder()
                    .name(uploadImageResponseDTO.getName())
                    .ext(uploadImageResponseDTO.getExt())
                    .size(uploadImageResponseDTO.getSize())
                    .createdAt(LocalDateTime.now())
                    .build());

            eventService.update(
                    eventResponseDTO.getId(),
                    EventUpdateDTO.builder().imageId(imageEntity.getId()).build());

            eventResponseDTO.setImageId(imageEntity.getId());

            return mapper.convertTo(imageEntity, ImageResponseDTO.class);
        }

        return mapper.convertTo(
                imageDAO.save(mapper.convertTo(new ImageCreateDTO(), ImageEntity.class)), ImageResponseDTO.class);
    }


    @Override
    public ImageResponseDTO update(Long id, ImageUpdateDTO imageUpdateDTO) {
        ImageEntity imageEntity = imageDAO.findById(id)
                .orElseThrow(() -> new NoSuchException("Image with ID = " + id + " not found in Database"));

        if (imageUpdateDTO.getName() != null) {
            imageEntity.setName(imageUpdateDTO.getName());
        }
        if (imageUpdateDTO.getExt() != null) {
            imageEntity.setExt(imageUpdateDTO.getExt());
        }
        if (imageUpdateDTO.getSize() != null) {
            imageEntity.setSize(imageUpdateDTO.getSize());
        }
        if (imageUpdateDTO.getAltText() != null) {
            imageEntity.setAltText(imageUpdateDTO.getAltText());
        }

        return imageMapper.convertEntityToDto(imageDAO.save(imageEntity));
    }

    @Override
    public Boolean delete(Long id) {
        if (!imageDAO.existsById(id)) {
            throw new NoSuchException("Image with ID = " + id + " not found in Database. " +
                    "Unable to delete an image that does not exist.");
        }
        imageDAO.deleteById(id);
        return true;
    }
}
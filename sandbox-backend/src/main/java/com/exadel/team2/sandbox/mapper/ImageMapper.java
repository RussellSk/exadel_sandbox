package com.exadel.team2.sandbox.mapper;

import com.exadel.team2.sandbox.entity.ImageEntity;
import com.exadel.team2.sandbox.web.image.ImageResponseDTO;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;



@Component
public class ImageMapper implements Mapper<ImageEntity, ImageResponseDTO>{

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ImageResponseDTO convertEntityToDto(ImageEntity entity) {
        return modelMapper.map(entity, ImageResponseDTO.class);
    }

    @Override
    public ImageEntity convertDtoToEntity(Object dto) {
        return modelMapper.map(dto, ImageEntity.class);
    }
}

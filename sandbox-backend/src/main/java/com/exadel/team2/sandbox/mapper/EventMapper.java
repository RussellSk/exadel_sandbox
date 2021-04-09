package com.exadel.team2.sandbox.mapper;

import com.exadel.team2.sandbox.entity.EventEntity;
import com.exadel.team2.sandbox.web.EventResponseDTO;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class EventMapper implements Mapper<EventEntity, EventResponseDTO> {

    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public EventResponseDTO convertEntityToDto(EventEntity entity) {
        return modelMapper.map(entity, EventResponseDTO.class);
    }

    @Override
    public EventEntity convertDtoToEntity(Object dto) {
        return modelMapper.map(dto, EventEntity.class);
    }
}

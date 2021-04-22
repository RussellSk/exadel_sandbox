package com.exadel.team2.sandbox.mapper;

import com.exadel.team2.sandbox.entity.EventTypeEntity;
import com.exadel.team2.sandbox.web.event_type.EventTypeResponseDTO;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventTypeMapper implements Mapper<EventTypeEntity, EventTypeResponseDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public EventTypeResponseDTO convertEntityToDto(EventTypeEntity entity) {
        return modelMapper.map(entity, EventTypeResponseDTO.class);
    }

    @Override
    public EventTypeEntity convertDtoToEntity(Object dto) {
        return modelMapper.map(dto, EventTypeEntity.class);
    }
}

package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EventTypeDAO;
import com.exadel.team2.sandbox.entity.EventTypeEntity;
import com.exadel.team2.sandbox.exceptions.NoSuchException;
import com.exadel.team2.sandbox.mapper.EventTypeMapper;
import com.exadel.team2.sandbox.service.EventTypeService;
import com.exadel.team2.sandbox.web.event_type.EventTypeCreateDTO;
import com.exadel.team2.sandbox.web.event_type.EventTypeResponseDTO;
import com.exadel.team2.sandbox.web.event_type.EventTypeUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class EventTypeServiceImpl implements EventTypeService {

    private final EventTypeDAO eventTypeDAO;
    private final EventTypeMapper eventTypeMapper;

    @Override
    public EventTypeResponseDTO getById(Long id) {
        EventTypeEntity eventTypeEntity = eventTypeDAO.findById(id)
                 .orElseThrow(() -> new NoSuchException("Event type with ID = " + id + " not found in Database" ));
        return eventTypeMapper.convertEntityToDto(eventTypeEntity);
    }

    @Override
    public List<EventTypeResponseDTO> getAll() {
        List<EventTypeEntity> eventTypeEntities = eventTypeDAO.findAll();
        if (eventTypeEntities.isEmpty()) {
            throw new NoSuchException("Not found event types in Database");
        }
        return eventTypeEntities.stream()
                .map(eventTypeMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public EventTypeResponseDTO save(EventTypeCreateDTO eventTypeCreateDTO) {
        EventTypeEntity eventTypeEntity = eventTypeMapper.convertDtoToEntity(eventTypeCreateDTO);

        eventTypeEntity.setCreatedAt(LocalDateTime.now());
        eventTypeDAO.save(eventTypeEntity);
        return eventTypeMapper.convertEntityToDto(eventTypeEntity);
    }

    @Override
    public EventTypeResponseDTO update(Long id, EventTypeUpdateDTO eventTypeUpdateDTO) {
        EventTypeEntity eventTypeEntity = eventTypeMapper.convertDtoToEntity(eventTypeUpdateDTO);
        eventTypeEntity.setId(id);
        if (!eventTypeDAO.existsById(id)) {
            throw new NoSuchException("Event type with ID = " + id + " not found in Database");
        }
        eventTypeEntity.setUpdatedAt(LocalDateTime.now());

        return eventTypeMapper.convertEntityToDto(eventTypeDAO.save(eventTypeEntity));

    }


    @Override
    public Boolean delete(Long id) {
        if (!eventTypeDAO.existsById(id)) {
            throw new NoSuchException("Event type with ID = " + id + " not found in Database. " +
                    "Unable to delete an event type that does not exist.");
        }
        eventTypeDAO.deleteById(id);
        return true;
    }
}

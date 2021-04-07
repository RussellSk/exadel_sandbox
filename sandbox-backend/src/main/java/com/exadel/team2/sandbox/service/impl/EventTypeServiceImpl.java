package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EventTypeDAO;
import com.exadel.team2.sandbox.entity.EventTypeEntity;
import com.exadel.team2.sandbox.mapper.EventTypeMapper;
import com.exadel.team2.sandbox.service.EventTypeService;
import com.exadel.team2.sandbox.web.EventTypeCreateDTO;
import com.exadel.team2.sandbox.web.EventTypeResponseDTO;
import com.exadel.team2.sandbox.web.EventTypeUpdateDTO;
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
public class EventTypeServiceImpl implements EventTypeService {

    private final EventTypeDAO eventTypeDAO;
    private final EventTypeMapper eventTypeMapper;

    @Override
    public EventTypeResponseDTO getById(Long id) {
        EventTypeEntity eventTypeEntity = eventTypeDAO.findById(id)
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Type not found"));
        return eventTypeMapper.convertEntityToDto(eventTypeEntity);
    }

    @Override
    public List<EventTypeResponseDTO> getAll() {
        List<EventTypeEntity> eventTypeEntities = eventTypeDAO.findAll();
        if (eventTypeEntities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Sorry, however haven't content");
        }
        return eventTypeEntities.stream()
                .map(eventTypeMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public EventTypeResponseDTO save(EventTypeCreateDTO eventTypeCreateDTO) {
        EventTypeEntity eventTypeEntity = eventTypeMapper.convertDtoToEntity(eventTypeCreateDTO);

//        eventTypeEntity.setEvtName(eventTypeCreateDTO.getName());
//        eventTypeEntity.setEvtDescription(eventTypeCreateDTO.getDescription());
        eventTypeEntity.setEvtCreatedAt(LocalDateTime.now());
        eventTypeDAO.save(eventTypeEntity);
        return eventTypeMapper.convertEntityToDto(eventTypeEntity);
    }

    @Override
    public EventTypeResponseDTO update(Long id, EventTypeUpdateDTO eventTypeUpdateDTO) {
        EventTypeEntity eventTypeEntity = eventTypeMapper.convertDtoToEntity(eventTypeUpdateDTO);
        eventTypeEntity.setEvtId(id);

//        EventTypeEntity eventTypeEntity = eventTypeDAO.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Type not found"));

//        eventTypeEntity.setEvtName(eventTypeUpdateDTO.getEvtName());
//        eventTypeEntity.setEvtDescription(eventTypeUpdateDTO.getEvtDescription());

        eventTypeEntity.setEvtUpdatedAt(LocalDateTime.now());

        return eventTypeMapper.convertEntityToDto(eventTypeDAO.save(eventTypeEntity));

    }


    @Override
    public void delete(Long id) {
        eventTypeDAO.deleteById(id);
    }
}

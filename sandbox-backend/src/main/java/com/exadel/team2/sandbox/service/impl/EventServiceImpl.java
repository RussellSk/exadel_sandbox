package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EventDAO;
import com.exadel.team2.sandbox.dto.EventDto;
import com.exadel.team2.sandbox.entity.EventEntity;
import com.exadel.team2.sandbox.entity.EventTypeEntity;
import com.exadel.team2.sandbox.entity.ImageEntity;
import com.exadel.team2.sandbox.service.EventService;
import com.exadel.team2.sandbox.service.EventTypeService;
import com.exadel.team2.sandbox.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventDAO eventDAO;
    private final ImageService imageService;
    private final EventTypeService eventTypeService;

    @Override
    public EventEntity getById(Long evId) {
        return eventDAO.findById(evId).orElse(null);
    }

    @Override
    public List<EventEntity> getAll() {
        return eventDAO.findAll();
    }

    @Override
    public EventEntity save(EventDto eventDto) {
        EventEntity eventEntity = new EventEntity();

        ImageEntity imageEntity = imageService.getById(eventDto.getImageId());
        if (eventEntity != null){
            eventEntity.setImage(imageEntity);
        }

        EventTypeEntity eventTypeEntity = eventTypeService.getById(eventDto.getEventTypeId());
        if (eventEntity != null){
            eventEntity.setEventType(eventTypeEntity);
        }

        eventEntity.setEvShortDescription(eventDto.getEvShortDescription());
        eventEntity.setEvFullDescription(eventDto.getEvFullDescription());
        eventEntity.setEvStartDate(eventDto.getEvStartDate());
        eventEntity.setEvDeadline(eventDto.getEvDeadline());
        eventEntity.setEvLocation(eventDto.getEvLocation());
        eventEntity.setEvCandidateRequirements(eventDto.getEvCandidateRequirements());
        eventEntity.setEvCreatedAt(LocalDateTime.now());

        return eventDAO.save(eventEntity);
    }

    @Override
    public EventEntity update(Long id, EventDto eventDto) {

        EventEntity eventEntity = getById(id);
        ImageEntity imageEntity = imageService.getById(eventDto.getImageId());
        eventEntity.setImage(imageEntity);
        EventTypeEntity eventTypeEntity = eventTypeService.getById(eventDto.getEventTypeId());
        eventEntity.setEventType(eventTypeEntity);
        eventEntity.setEvShortDescription(eventDto.getEvShortDescription());
        eventEntity.setEvFullDescription(eventDto.getEvFullDescription());
        eventEntity.setEvStartDate(eventDto.getEvStartDate());
        eventEntity.setEvDeadline(eventDto.getEvDeadline());
        eventEntity.setEvLocation(eventDto.getEvLocation());
        eventEntity.setEvCandidateRequirements(eventDto.getEvCandidateRequirements());
        eventEntity.setEvUpdatedAt(LocalDateTime.now());

        return eventDAO.save(eventEntity);
    }

    @Override
    public void delete(Long evId) {
        eventDAO.deleteById(evId);
    }
}
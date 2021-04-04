package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EventDAO;
import com.exadel.team2.sandbox.dto.EventСreateDTO;
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
    public EventEntity getById(Long id) {
        return eventDAO.findById(id).orElse(null);
    }

    @Override
    public List<EventEntity> getAll() {
        return eventDAO.findAll();
    }

    @Override
    public EventEntity save(EventEntity eventEntity) {
        return eventDAO.save(eventEntity);
    }

//    @Override
//    public EventEntity update(Long id, EventСreateDTO EventСreateDto) {
//
//        EventEntity eventEntity = getById(id);
//        ImageEntity imageEntity = imageService.getById(EventСreateDto.getImageId());
//        eventEntity.setImage(imageEntity);
//        EventTypeEntity eventTypeEntity = eventTypeService.getById(EventСreateDto.getEventTypeId());
//        eventEntity.setEventType(eventTypeEntity);
//        eventEntity.setEvShortDescription(EventСreateDto.getEvShortDescription());
//        eventEntity.setEvFullDescription(EventСreateDto.getEvFullDescription());
//        eventEntity.setEvStartDate(EventСreateDto.getEvStartDate());
//        eventEntity.setEvDeadline(EventСreateDto.getEvDeadline());
//        eventEntity.setEvLocation(EventСreateDto.getEvLocation());
//        eventEntity.setEvCandidateRequirements(EventСreateDto.getEvCandidateRequirements());
//        eventEntity.setEvUpdatedAt(LocalDateTime.now());
//
//        return eventDAO.save(eventEntity);
//    }

    @Override
    public void delete(Long id) {
        eventDAO.deleteById(id);
    }
}
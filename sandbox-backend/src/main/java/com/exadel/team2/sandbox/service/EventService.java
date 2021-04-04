package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.EventСreateDTO;
import com.exadel.team2.sandbox.entity.EventEntity;
import java.util.List;

public interface EventService {

    EventEntity getById (Long id);

    List <EventEntity> getAll();

    EventEntity save(EventEntity eventEntity);

//    EventEntity update(Long id, EventСreateDTO EventСreateDto);

    void delete(Long id);

}




package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.dto.EventDto;
import com.exadel.team2.sandbox.entity.EventEntity;
import java.util.List;

public interface EventService {

    EventEntity getById (Long evId);

    List <EventEntity> getAll();

    EventEntity save(EventDto eventDto);

    EventEntity update(Long id, EventDto eventDto);

    void delete(Long evId);

}




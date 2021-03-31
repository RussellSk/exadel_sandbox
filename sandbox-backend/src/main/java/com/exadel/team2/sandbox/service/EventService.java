package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.EventEntity;
import java.util.List;

public interface EventService {

    EventEntity getById (Long evId);

    List <EventEntity> getAll();

    EventEntity save(EventEntity eventEntity);

    EventEntity update(EventEntity eventEntity);

    void delete(Long evId);

}




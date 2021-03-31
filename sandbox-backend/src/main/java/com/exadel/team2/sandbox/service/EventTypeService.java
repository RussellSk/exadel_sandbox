package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.EventTypeEntity;
import java.util.List;

public interface EventTypeService {

    EventTypeEntity getById (Long evtId);

    List<EventTypeEntity> getAll();

    EventTypeEntity save(EventTypeEntity eventTypeEntity);

    EventTypeEntity update(EventTypeEntity eventTypeEntity);

    void delete(Long evtId);
}

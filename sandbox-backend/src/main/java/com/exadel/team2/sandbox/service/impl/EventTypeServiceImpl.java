package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EventTypeDAO;
import com.exadel.team2.sandbox.entity.EventTypeEntity;
import com.exadel.team2.sandbox.service.EventTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class EventTypeServiceImpl implements EventTypeService {

    private final EventTypeDAO eventTypeDAO;

    @Override
    public EventTypeEntity getById(Long evtId) {
        return eventTypeDAO.findById(evtId).orElse(null);
    }

    @Override
    public List<EventTypeEntity> getAll() {
        return eventTypeDAO.findAll();
    }

    @Override
    public EventTypeEntity save(EventTypeEntity eventTypeEntity) {
        return eventTypeDAO.save(eventTypeEntity);
    }

    @Override
    public EventTypeEntity update(EventTypeEntity eventTypeEntity) {
        return eventTypeDAO.save(eventTypeEntity);
    }

    @Override
    public void delete(Long evtId) {
        eventTypeDAO.deleteById(evtId);
    }
}

package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EventDAO;
import com.exadel.team2.sandbox.entity.EventEntity;
import com.exadel.team2.sandbox.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventDAO eventDAO;

    @Override
    public EventEntity getById(Long evId) {
        return eventDAO.findById(evId).orElse(null);
    }

    @Override
    public List<EventEntity> getAll() {
        return eventDAO.findAll();
    }

    @Override
    public EventEntity save(EventEntity eventEntity) {
        return eventDAO.save(eventEntity);
    }

    @Override
    public EventEntity update(EventEntity eventEntity) {
        return eventDAO.save(eventEntity);
    }

    @Override
    public void delete(Long evId) {
        eventDAO.deleteById(evId);
    }
}
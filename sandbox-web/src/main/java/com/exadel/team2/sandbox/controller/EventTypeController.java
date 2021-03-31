package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.EventTypeEntity;
import com.exadel.team2.sandbox.service.EventTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-type")
public class EventTypeController {

    private final EventTypeService eventTypeService;

    @GetMapping("/{id}")
    public EventTypeEntity getEventType(@PathVariable Long id) {
        return eventTypeService.getById(id);
    }

    @GetMapping("/all")
    public List<EventTypeEntity> getAllEventType() {
        return eventTypeService.getAll();
    }

    @PostMapping
    public EventTypeEntity saveEventType(@RequestBody EventTypeEntity eventTypeEntity) {
        return eventTypeService.save(eventTypeEntity);
    }

    @PutMapping("/{id}")
    public EventTypeEntity updateEventType(@PathVariable Long id, @RequestBody EventTypeEntity eventTypeEntity) {
        eventTypeEntity.setEvtId(id);
        return eventTypeService.update(eventTypeEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteEventType(@PathVariable Long id) {
        eventTypeService.delete(id);
    }

}

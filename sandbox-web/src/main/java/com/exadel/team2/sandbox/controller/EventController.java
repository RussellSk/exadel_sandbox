package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.EventEntity;
import com.exadel.team2.sandbox.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    public EventEntity getEvent(@PathVariable Long id){
        return  eventService.getById(id);
    }

    @GetMapping("/all")
    public List<EventEntity> getAllEvent() {
        return eventService.getAll();
    }

    @PostMapping
    public EventEntity saveEvent(@RequestBody EventEntity eventEntity) {
        return eventService.save(eventEntity);
    }

    @PutMapping("/{id}")
    public EventEntity updateEvent(@PathVariable Long id, @RequestBody EventEntity eventEntity) {
        eventEntity.setEvId(id);
        return eventService.update(eventEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }

}

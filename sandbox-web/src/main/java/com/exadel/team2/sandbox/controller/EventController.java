package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.dto.EventDto;
import com.exadel.team2.sandbox.entity.EventEntity;
import com.exadel.team2.sandbox.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public EventEntity saveEvent(@RequestBody EventDto eventDto) {
        return eventService.save(eventDto);
    }

    @PutMapping("/{id}")
    public EventEntity updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        eventDto.setEvId(id);
        return eventService.update(id, eventDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        if (eventService.getById(id) == null) {
            return  ResponseEntity.notFound().build();
        }
        eventService.delete(id);
        return ResponseEntity.ok().build();
    }

}

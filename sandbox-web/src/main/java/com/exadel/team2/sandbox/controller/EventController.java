package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.web.EventCreateDTO;
import com.exadel.team2.sandbox.service.EventService;
import com.exadel.team2.sandbox.web.EventResponseDTO;
import com.exadel.team2.sandbox.web.EventUpdateDTO;
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
    public EventResponseDTO getEventById(@PathVariable Long id) {
        return eventService.getById(id);
    }

    @GetMapping("/all")
    public List<EventResponseDTO> getAllEvents() {
        return eventService.getAll();
    }

    @PostMapping
    public EventResponseDTO createEvent(@RequestBody EventCreateDTO eventCreateDTO) {
        return eventService.save(eventCreateDTO);
    }

    @PutMapping("/{id}")
    public EventResponseDTO updateEvent(@PathVariable Long id, EventUpdateDTO eventUpdateDTO) {
        return eventService.update(id, eventUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        if (eventService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        eventService.delete(id);
        return ResponseEntity.ok().build();
    }

}

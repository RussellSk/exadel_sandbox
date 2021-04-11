package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.web.EventCreateDTO;
import com.exadel.team2.sandbox.service.EventService;
import com.exadel.team2.sandbox.web.EventResponseDTO;
import com.exadel.team2.sandbox.web.EventUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
    public Page<EventResponseDTO> getAllEventsWithRsql(
            @RequestParam(value = "search") String search,
            @RequestParam(defaultValue = "0", name = "page") Integer page,
            @RequestParam(defaultValue = "9", name = "numberOfEventsPerPage") Integer number) {
        return eventService.getAllPageable(PageRequest.of(page, number), search);
    }

    @PostMapping
    public EventResponseDTO createEvent(@Validated @RequestBody EventCreateDTO eventCreateDTO) {
        return eventService.save(eventCreateDTO);
    }

    @PutMapping("/{id}")

    public EventResponseDTO updateEvent(@Validated @PathVariable Long id, @RequestBody EventUpdateDTO eventUpdateDTO) {
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
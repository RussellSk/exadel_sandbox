package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.EventTypeService;
import com.exadel.team2.sandbox.web.EventTypeCreateDTO;
import com.exadel.team2.sandbox.web.EventTypeResponseDTO;
import com.exadel.team2.sandbox.web.EventTypeUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-type")
public class EventTypeController {

    private final EventTypeService eventTypeService;

    @GetMapping("/{id}")
    public EventTypeResponseDTO getEventTypeById(@PathVariable Long id) {
        return eventTypeService.getById(id);
    }

    @GetMapping("/all")
    public List<EventTypeResponseDTO> getAllEventType() {
        return eventTypeService.getAll();
    }

    @PostMapping
    public EventTypeResponseDTO saveEventType(@Validated @RequestBody EventTypeCreateDTO eventTypeCreateDTO) {
        return eventTypeService.save(eventTypeCreateDTO);
    }

    @PutMapping("/{id}")
    public EventTypeResponseDTO updateEventType(@Validated @PathVariable Long id, @RequestBody EventTypeUpdateDTO eventTypeUpdateDTO) {
        return eventTypeService.update(id, eventTypeUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteEventType(@PathVariable Long id) {
        return eventTypeService.delete(id);
    }

}

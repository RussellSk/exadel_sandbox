package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.dto.EventResponseDTO;
import com.exadel.team2.sandbox.dto.EventСreateDTO;
import com.exadel.team2.sandbox.entity.EventEntity;
import com.exadel.team2.sandbox.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    @ResponseBody
    public EventСreateDTO getEventById(@PathVariable Long id){
        return convertToEventСreateDto(eventService.getById(id));
    }

//    @GetMapping("/all")
//    @ResponseBody
//    public List<EventEntity> getAllEvent() {
//        return eventService.getAll().stream()
//                .map(eventEntity -> modelMapper.map(eventEntity, EventСreateDTO.class))
//                .collect(Collectors.toList());
////        return eventService.getAll();
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EventСreateDTO createEvent(@RequestBody EventСreateDTO eventСreateDTO) {
        EventEntity eventEntity = convertToEventEntity(eventСreateDTO);
        EventEntity eventCreated = eventService.save(eventEntity);
        return convertToEventСreateDto(eventCreated);
    }

//    @PutMapping("/{id}")
//    public EventEntity updateEvent(@PathVariable Long id, @RequestBody EventСreateDTO EventСreateDto) {
//        EventСreateDto.setId(id);
//        return eventService.update(id, EventСreateDto);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        if (eventService.getById(id) == null) {
            return  ResponseEntity.notFound().build();
        }
        eventService.delete(id);
        return ResponseEntity.ok().build();
    }


    private EventСreateDTO convertToEventСreateDto(EventEntity eventEntity) {
        EventСreateDTO eventСreateDTO = modelMapper.map(eventEntity, EventСreateDTO.class);
//        eventСreateDTO.setSubmissionDate(eventEntity.getSubmissionDate(),
//                eventService.getCurrentUser().getPreference().getTimezone());
        return eventСreateDTO;
    }

    private EventEntity convertToEventEntity(EventСreateDTO eventСreateDTO) {
        EventEntity eventEntity = modelMapper.map(eventСreateDTO, EventEntity.class);
        return eventEntity;
    }




}

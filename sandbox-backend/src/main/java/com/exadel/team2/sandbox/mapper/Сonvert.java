//package com.exadel.team2.sandbox.mapper;
//
//import com.exadel.team2.sandbox.dto.EventСreateDTO;
//import com.exadel.team2.sandbox.entity.EventEntity;
//import com.exadel.team2.sandbox.service.EventService;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//
//@RequiredArgsConstructor
//public class Сonvert {
//
//    private final EventService eventService;
//    private final ModelMapper modelMapper;
//
//    private EventСreateDTO convertToEventСreateDto(EventEntity eventEntity) {
//        EventСreateDTO eventСreateDTO = modelMapper.map(eventEntity, EventСreateDTO.class);
////        eventСreateDTO.setSubmissionDate(eventEntity.getSubmissionDate(),
////                eventService.getCurrentUser().getPreference().getTimezone());
//        return eventСreateDTO;
//    }
//
//    private EventEntity convertToEventEntity(EventСreateDTO eventСreateDTO) {
//        EventEntity eventEntity = modelMapper.map(eventСreateDTO, EventEntity.class);
//        return eventEntity;
//    }
//}

//@GetMapping("/all")
//@ResponseBody
//public List<EventEntity> getAllEvent() {
////        return convertToEventСreateDto(eventService.getAll());
//        return eventService.getAll();
//        }
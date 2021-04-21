package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.event.EventCreateDTO;
import com.exadel.team2.sandbox.web.event.EventResponseDTO;
import com.exadel.team2.sandbox.web.event.EventUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {

    EventResponseDTO getById (Long id);
    List <EventResponseDTO> getAll();
    Page<EventResponseDTO> getAllPageable(Pageable pageable, String search);
    EventResponseDTO save(EventCreateDTO eventCreateDTO);
    EventResponseDTO update(Long id, EventUpdateDTO eventUpdateDTO);
    Boolean delete(Long id);

}




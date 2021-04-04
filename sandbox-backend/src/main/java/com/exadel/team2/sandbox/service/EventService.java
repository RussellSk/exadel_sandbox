package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.EventCreateDTO;
import com.exadel.team2.sandbox.web.EventResponseDTO;
import com.exadel.team2.sandbox.web.EventUpdateDTO;

import java.util.List;

public interface EventService {

    EventResponseDTO getById (Long id);
    List <EventResponseDTO> getAll();
    EventResponseDTO save(EventCreateDTO eventCreateDTO);
    EventResponseDTO update(Long id, EventUpdateDTO eventUpdateDTO);
    void delete(Long id);

}




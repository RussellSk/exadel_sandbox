package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.EventTypeCreateDTO;
import com.exadel.team2.sandbox.web.EventTypeResponseDTO;
import com.exadel.team2.sandbox.web.EventTypeUpdateDTO;

import java.util.List;

public interface EventTypeService {

    EventTypeResponseDTO getById (Long Id);
    List<EventTypeResponseDTO> getAll();
    EventTypeResponseDTO save(EventTypeCreateDTO eventTypeCreateDTO);
    EventTypeResponseDTO update(Long id, EventTypeUpdateDTO eventTypeUpdateDTO);
    Boolean delete(Long evtId);
}

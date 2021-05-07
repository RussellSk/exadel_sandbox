package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.BaseTestClass;
import com.exadel.team2.sandbox.dao.EventTypeDAO;
import com.exadel.team2.sandbox.entity.EventTypeEntity;
import com.exadel.team2.sandbox.exceptions.NoSuchException;
import com.exadel.team2.sandbox.mapper.EventMapper;
import com.exadel.team2.sandbox.mapper.EventTypeMapper;
import com.exadel.team2.sandbox.service.EventTypeService;
import com.exadel.team2.sandbox.web.event_type.EventTypeResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {EventTypeServiceImpl.class, EventTypeMapper.class})
class EventTypeServiceImplTest extends BaseTestClass {

    private static final long EVENT_TYPE_ID = 2L;

    @MockBean
    private EventTypeDAO eventTypeDAO;

    @Autowired
    private EventTypeService eventTypeService;


    @Test
    void getById_eventTypeExists_ok() {
        when(eventTypeDAO.findById(EVENT_TYPE_ID)).thenReturn(getEventTypeResponseEntity());

        EventTypeResponseDTO actualEventTypeResponse = eventTypeService.getById(EVENT_TYPE_ID);

        Assertions.assertEquals(EVENT_TYPE_ID, actualEventTypeResponse.getId());
    }


    @Test
    void getById_noEventType_exceptionThrown() {
        when(eventTypeDAO.findById(EVENT_TYPE_ID)).thenReturn(Optional.empty());

        assertThrows(NoSuchException.class, () -> eventTypeService.getById(EVENT_TYPE_ID));
    }


    @Test
    void getAll_eventTypeExistInDB_ok() {
        List<EventTypeEntity> eventTypeEntities = createEventTypeEntities();
        when(eventTypeDAO.findAll()).thenReturn(eventTypeEntities);

        List<EventTypeResponseDTO> actualEventType = eventTypeService.getAll();

        List<EventTypeResponseDTO> expectedEventTypes = createEventTypeDto();
        Assertions.assertEquals(expectedEventTypes, actualEventType);
    }


    @Test
    void getAll_eventTypeDoesNotExist_exceptionThrown() {
        List<EventTypeEntity> emptyEntity = createEmptyEntity();
        when(eventTypeDAO.findAll()).thenReturn(emptyEntity);

        assertThrows(NoSuchException.class, () -> eventTypeService.getAll());
    }


    @Test
    void deleteById_eventTypeExists_ok() {
        when(eventTypeDAO.existsById(EVENT_TYPE_ID)).thenReturn(true);

        boolean status = eventTypeService.delete(EVENT_TYPE_ID);

        verify(eventTypeDAO).deleteById(EVENT_TYPE_ID);
        Assertions.assertTrue(status);
    }


    @Test
    void deleteById_imageDoesNotExist_exceptionThrown() {
        when(eventTypeDAO.existsById(EVENT_TYPE_ID)).thenReturn(false);

        assertThrows(NoSuchException.class, () -> eventTypeService.delete(EVENT_TYPE_ID));
    }


    private Optional<EventTypeEntity> getEventTypeResponseEntity() {
        EventTypeEntity eventTypeEntity = new EventTypeEntity();
        eventTypeEntity.setId(EVENT_TYPE_ID);
        eventTypeEntity.setName("Java and JS");
        eventTypeEntity.setDescription("Description");
        return Optional.of(eventTypeEntity);
    }

    private List<EventTypeEntity> createEventTypeEntities() {
        EventTypeEntity eventTypeEntity1 = new EventTypeEntity();
        eventTypeEntity1.setId(1L);

        EventTypeEntity eventTypeEntity2 = new EventTypeEntity();
        eventTypeEntity2.setId(2L);

        EventTypeEntity eventTypeEntity3 = new EventTypeEntity();
        eventTypeEntity3.setId(3L);

        List<EventTypeEntity> eventTypeEntities = new ArrayList<>();
        eventTypeEntities.add(eventTypeEntity1);
        eventTypeEntities.add(eventTypeEntity2);
        eventTypeEntities.add(eventTypeEntity3);

        return eventTypeEntities;
    }

    private List<EventTypeResponseDTO> createEventTypeDto() {
        EventTypeResponseDTO eventTypeResponseDTO1 = new EventTypeResponseDTO();
        eventTypeResponseDTO1.setId(1L);

        EventTypeResponseDTO eventTypeResponseDTO2 = new EventTypeResponseDTO();
        eventTypeResponseDTO2.setId(2L);

        EventTypeResponseDTO eventTypeResponseDTO3 = new EventTypeResponseDTO();
        eventTypeResponseDTO3.setId(3L);

        List<EventTypeResponseDTO> eventTypeResponseDTOs = new ArrayList<>();
        eventTypeResponseDTOs.add(eventTypeResponseDTO1);
        eventTypeResponseDTOs.add(eventTypeResponseDTO2);
        eventTypeResponseDTOs.add(eventTypeResponseDTO3);

        return eventTypeResponseDTOs;
    }

    private List<EventTypeEntity> createEmptyEntity() {
        List<EventTypeEntity> eventTypeEntities = new ArrayList<>();

        return eventTypeEntities;
    }
}
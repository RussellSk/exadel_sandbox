package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EventTypeDAO;
import com.exadel.team2.sandbox.entity.EventTypeEntity;
import com.exadel.team2.sandbox.exceptions.NoSuchException;
import com.exadel.team2.sandbox.web.event_type.EventTypeResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EventTypeServiceImplTest {

    private static final long DUMMY_ID = 2L;

    @MockBean
    private EventTypeDAO eventTypeDAO;

    @Autowired
    private EventTypeServiceImpl eventTypeServiceImpl;


    @Test
    void getById_eventTypeExists_ok() {
        when(eventTypeDAO.findById(DUMMY_ID)).thenReturn(getEventTypeResponseEntity());

        EventTypeResponseDTO actualEventTypeResponse = eventTypeServiceImpl.getById(DUMMY_ID);

        Assertions.assertEquals(DUMMY_ID, actualEventTypeResponse.getId());
    }


    @Test
    void getById_noEventType_exceptionThrown() {
        when(eventTypeDAO.findById(DUMMY_ID)).thenReturn(Optional.empty());

        assertThrows(NoSuchException.class, () -> eventTypeServiceImpl.getById(DUMMY_ID));
    }


    @Test
    void getAll_eventTypeExistInDB_ok() {
        List<EventTypeEntity> eventTypeEntities = createEventTypeEntities();
        when(eventTypeDAO.findAll()).thenReturn(eventTypeEntities);

        List<EventTypeResponseDTO> actualEventType = eventTypeServiceImpl.getAll();

        List<EventTypeResponseDTO> expectedEventTypes = createEventTypeDto();
        Assertions.assertEquals(expectedEventTypes, actualEventType);
    }


    @Test
    void getAll_eventTypeDoesNotExist_exceptionThrown() {
        List<EventTypeEntity> emptyEntity = createEmptyEntity();
        when(eventTypeDAO.findAll()).thenReturn(emptyEntity);

        assertThrows(NoSuchException.class, () -> eventTypeServiceImpl.getAll());
    }


    @Test
    void deleteById_eventTypeExists_ok() {
        when(eventTypeDAO.existsById(DUMMY_ID)).thenReturn(true);

        boolean status = eventTypeServiceImpl.delete(DUMMY_ID);

        verify(eventTypeDAO).deleteById(DUMMY_ID);
        Assertions.assertTrue(status);
    }


    @Test
    void deleteById_imageDoesNotExist_exceptionThrown() {
        when(eventTypeDAO.existsById(DUMMY_ID)).thenReturn(false);

        assertThrows(NoSuchException.class, () -> eventTypeServiceImpl.delete(DUMMY_ID));
    }


    private Optional<EventTypeEntity> getEventTypeResponseEntity() {
        EventTypeEntity eventTypeEntity = new EventTypeEntity();
        eventTypeEntity.setId(DUMMY_ID);
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
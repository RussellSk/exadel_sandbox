package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EventDAO;
import com.exadel.team2.sandbox.entity.EventEntity;
import com.exadel.team2.sandbox.exceptions.NoSuchException;
import com.exadel.team2.sandbox.web.event.EventResponseDTO;
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
class EventServiceImplTest {

    private static final long DUMMY_ID = 1L;

    @MockBean
    private EventDAO eventDAO;

    @Autowired
    private EventServiceImpl eventServiceImpl;

    @Test
    void getById_eventExists_ok() {
        when(eventDAO.findById(DUMMY_ID)).thenReturn(getEventResponseEntity());

        EventResponseDTO actualEventResponse = eventServiceImpl.getById(DUMMY_ID);

        Assertions.assertEquals(DUMMY_ID, actualEventResponse.getId());
    }


    @Test
    void getById_noEvent_exceptionThrown() {
        when(eventDAO.findById(DUMMY_ID)).thenReturn(Optional.empty());

        assertThrows(NoSuchException.class, () -> eventServiceImpl.getById(DUMMY_ID));
    }


    @Test
    void getAll_eventExistInDB_ok() {
        List<EventEntity> eventEntities = createEventEntities();
        when(eventDAO.findAll()).thenReturn(eventEntities);

        List<EventResponseDTO> actualEvents = eventServiceImpl.getAll();

        List<EventResponseDTO> expectedEvents = createEventDto();
        Assertions.assertEquals(expectedEvents, actualEvents);
    }


    @Test
    void getAll_eventsDoesNotExist_exceptionThrown() {
        List<EventEntity> emptyEntity = createEmptyEntity();
        when(eventDAO.findAll()).thenReturn(emptyEntity);

        assertThrows(NoSuchException.class, () -> eventServiceImpl.getAll());
    }


    @Test
    void getAll_imagesDoesNotExist_thrownNullPointerException() {
        when(eventDAO.findAll()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> eventServiceImpl.getAll());
    }


    @Test
    void deleteById_eventExists_ok() {
        when(eventDAO.existsById(DUMMY_ID)).thenReturn(true);

        boolean status = eventServiceImpl.delete(DUMMY_ID);

        verify(eventDAO).deleteById(DUMMY_ID);
        Assertions.assertTrue(status);
    }


    @Test
    void deleteById_imageDoesNotExist_exceptionThrown() {
        when(eventDAO.existsById(DUMMY_ID)).thenReturn(false);

        assertThrows(NoSuchException.class, () -> eventServiceImpl.delete(DUMMY_ID));
    }


    private Optional<EventEntity> getEventResponseEntity() {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(DUMMY_ID);

        return Optional.of(eventEntity);
    }

    private List<EventEntity> createEventEntities() {
        EventEntity eventEntity1 = new EventEntity();
        eventEntity1.setId(1L);

        EventEntity eventEntity2 = new EventEntity();
        eventEntity2.setId(2L);

        EventEntity eventEntity3 = new EventEntity();
        eventEntity3.setId(3L);

        List<EventEntity> eventEntities = new ArrayList<>();
        eventEntities.add(eventEntity1);
        eventEntities.add(eventEntity2);
        eventEntities.add(eventEntity3);

        return eventEntities;
    }


    private List<EventResponseDTO> createEventDto() {
        EventResponseDTO eventResponseDTO1 = new EventResponseDTO();
        eventResponseDTO1.setId(1L);

        EventResponseDTO eventResponseDTO2 = new EventResponseDTO();
        eventResponseDTO2.setId(2L);

        EventResponseDTO eventResponseDTO3 = new EventResponseDTO();
        eventResponseDTO3.setId(3L);

        List<EventResponseDTO> eventResponseDTOs = new ArrayList<>();
        eventResponseDTOs.add(eventResponseDTO1);
        eventResponseDTOs.add(eventResponseDTO2);
        eventResponseDTOs.add(eventResponseDTO3);

        return eventResponseDTOs;
    }

    private List<EventEntity> createEmptyEntity() {
        List<EventEntity> eventEntities = new ArrayList<>();

        return eventEntities;
    }

}
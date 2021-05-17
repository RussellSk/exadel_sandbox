package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.configuration.security.JwtTokenFilter;
import com.exadel.team2.sandbox.configuration.security.SecurityConfig;
import com.exadel.team2.sandbox.service.EventService;
import com.exadel.team2.sandbox.web.event.EventResponseDTO;
import com.exadel.team2.sandbox.web.event.EventUpdateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {EventController.class})
class EventControllerTest {

    private static final long EVENT_ID = 6L;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private EventService eventService;

    @Autowired
    private EventController eventController;

    @BeforeEach
    void setUp() {
        eventService = mock(EventService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new EventController(eventService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getById_eventExists_ok() throws Exception {
        when(eventService.getById(EVENT_ID)).thenReturn(responseDto());

        mockMvc.perform(get("/event/{id}", EVENT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto())))
                .andExpect(status().isOk());
    }

    @Test
    void getAll_eventInDb_ok() throws Exception {
        int page = 0;
        int itemsPerPage = 9;
        String search = "";
        Pageable pageable = PageRequest.of(page, itemsPerPage);

        when(eventService.getAllPageable(pageable, search)).thenReturn(createResponseEventDTOPage());

        mockMvc.perform(get("/event/all?page={page}&itemsPerPage={itemsPerPage}", page, itemsPerPage)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void update_eventExists_ok() throws Exception {
        String json = objectMapper.writeValueAsString(updateDto());

        EventUpdateDTO eventUpdateDTO = updateDto();
        when(eventService.update(EVENT_ID, eventUpdateDTO)).thenReturn(responseDto());

        mockMvc.perform(put("/event/{id}", EVENT_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().isOk());

        verify(eventService, times(1)).update(EVENT_ID, eventUpdateDTO);
    }


    @Test
    void deleteById_eventTypeExists_ok() throws Exception {
        when(eventService.delete(EVENT_ID)).thenReturn(true);

        mockMvc.perform(delete("/event/{id}", EVENT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }


    private EventResponseDTO responseDto() {
        EventResponseDTO eventDTO = new EventResponseDTO();
        eventDTO.setId(EVENT_ID);
        eventDTO.setCity("Lviv");
        eventDTO.setCountry("Ukraine");
        return eventDTO;
    }

    private EventUpdateDTO updateDto() {
        EventUpdateDTO updateDTO = new EventUpdateDTO();
        updateDTO.setCity("Vinnitsa");
        updateDTO.setDescription("Description");
        return updateDTO;
    }

    private Page<EventResponseDTO> createResponseEventDTOPage() {
        EventResponseDTO eventResponseDTO1 = new EventResponseDTO();
        eventResponseDTO1.setId(1L);

        EventResponseDTO eventResponseDTO2 = new EventResponseDTO();
        eventResponseDTO2.setId(2L);

        EventResponseDTO eventResponseDTO3 = new EventResponseDTO();
        eventResponseDTO3.setId(3L);

        return new PageImpl<>(List.of(eventResponseDTO1, eventResponseDTO2, eventResponseDTO3));
    }
}
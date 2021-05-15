//package com.exadel.team2.sandbox.controller;
//
//import com.exadel.team2.sandbox.service.EventTypeService;
//import com.exadel.team2.sandbox.web.event_type.EventTypeResponseDTO;
//import com.exadel.team2.sandbox.web.event_type.EventTypeUpdateDTO;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(EventTypeController.class)
//class EventTypeControllerTest {
//
//    private static final long EVENT_TYPE_ID = 5L;
//
//    private MockMvc mockMvc;
//
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private EventTypeService eventTypeService;
//
//    @Autowired
//    private EventTypeController eventTypeController;
//
//    @BeforeEach
//    void setUp() {
//        eventTypeService = mock(EventTypeService.class);
//        mockMvc = MockMvcBuilders.standaloneSetup(new EventTypeController(eventTypeService)).build();
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void getById_eventTypeExists_ok() throws Exception {
//        when(eventTypeService.getById(EVENT_TYPE_ID)).thenReturn(responseDto());
//
//        mockMvc.perform(get("/event-type/{id}", EVENT_TYPE_ID)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(responseDto())))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void gelAll_eventTypeInDb_ok() throws Exception {
//        List<EventTypeResponseDTO> eventTypeEntities = createListEventTypeDto();
//        when(eventTypeService.getAll()).thenReturn(eventTypeEntities);
//
//        mockMvc.perform(get("/event-type/all")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(eventTypeEntities)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void save_eventTypeInDB_ok() throws Exception {
//        when(eventTypeService.save(any())).thenReturn(responseDto());
//
//        mockMvc.perform(post("/event-type")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(responseDto())))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(responseDto())));
//    }
//
//
//    @Test
//    void update_eventTypeExists_ok() throws Exception {
//        String json = objectMapper.writeValueAsString(updateDto());
//
//        EventTypeUpdateDTO eventTypeUpdateDTO = updateDto();
//        when(eventTypeService.update(EVENT_TYPE_ID, eventTypeUpdateDTO)).thenReturn(responseDto());
//
//        mockMvc.perform(put("/event-type/{id}", EVENT_TYPE_ID)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
//                .andExpect(status().isOk());
//
//        verify(eventTypeService, times(1)).update(EVENT_TYPE_ID, eventTypeUpdateDTO);
//    }
//
//
//    @Test
//    void deleteById_eventTypeExists_ok() throws Exception {
//        when(eventTypeService.delete(EVENT_TYPE_ID)).thenReturn(true);
//
//        mockMvc.perform(delete("/event-type/{id}", EVENT_TYPE_ID)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful());
//    }
//
//
//    private EventTypeResponseDTO responseDto() {
//        EventTypeResponseDTO eventTypeDTO = new EventTypeResponseDTO();
//        eventTypeDTO.setId(EVENT_TYPE_ID);
//        eventTypeDTO.setName("Java and JS");
//        eventTypeDTO.setDescription("Description");
//        return eventTypeDTO;
//    }
//
//    private EventTypeUpdateDTO updateDto() {
//        EventTypeUpdateDTO updateDTO = new EventTypeUpdateDTO();
//        updateDTO.setName("Java and JS");
//        updateDTO.setDescription("Description");
//        return updateDTO;
//    }
//
//    private List<EventTypeResponseDTO> createListEventTypeDto() {
//        EventTypeResponseDTO eventTypeResponseDTO1 = new EventTypeResponseDTO();
//        eventTypeResponseDTO1.setId(1L);
//
//        EventTypeResponseDTO eventTypeResponseDTO2 = new EventTypeResponseDTO();
//        eventTypeResponseDTO2.setId(2L);
//
//        EventTypeResponseDTO eventTypeResponseDTO3 = new EventTypeResponseDTO();
//        eventTypeResponseDTO3.setId(3L);
//
//        List<EventTypeResponseDTO> eventTypeResponseDTOs = new ArrayList<EventTypeResponseDTO>();
//        eventTypeResponseDTOs.add(eventTypeResponseDTO1);
//        eventTypeResponseDTOs.add(eventTypeResponseDTO2);
//        eventTypeResponseDTOs.add(eventTypeResponseDTO3);
//
//        return eventTypeResponseDTOs;
//    }
//}
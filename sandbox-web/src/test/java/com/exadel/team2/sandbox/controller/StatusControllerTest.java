package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.Status;
import com.exadel.team2.sandbox.service.StatusService;
import com.exadel.team2.sandbox.web.status.CreateStatusDTO;
import com.exadel.team2.sandbox.web.status.ResponseStatusDTO;
import com.exadel.team2.sandbox.web.status.UpdateStatusDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {StatusController.class})
class StatusControllerTest {

    private static final Long ST_ID = 1L;
    private static final String NAME = "name";
    private static final String DESCRIPTION = "desc";

    private MockMvc mockMvc;

    @MockBean
    private StatusService statusService;

    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        statusService = mock(StatusService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new StatusController(statusService)).build();
        mapper.registerModule(new JavaTimeModule());
    }


    @Test
    void update_ShouldUpdateSTAndReturnResponseSTDTO_WhenStatusNotNull() throws Exception {
        String json = mapper.writeValueAsString(createUpdateStatusDTO());

        when(statusService.update(ST_ID, createUpdateStatusDTO())).thenReturn(createResponseStatusDTO());

        MvcResult mvcResult = mockMvc.perform(put("/status/{id}", ST_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ResponseStatusDTO responseStatusDTO = mapper.readValue(response, ResponseStatusDTO.class);

        assertNotNull(responseStatusDTO);
        assertEquals(responseStatusDTO.getId(), ST_ID);
        verify(statusService, times(1)).update(ST_ID, createUpdateStatusDTO());
    }

    @Test
    void save_ShouldSaveSTAndReturnResponseSTDTO_WhenStatusNotNull() throws Exception {
        String json = mapper.writeValueAsString(createCreateStatusDTO());

        when(statusService.save(createCreateStatusDTO())).thenReturn(createResponseStatusDTO());
        MvcResult mvcResult = mockMvc.perform(post("/status").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ResponseStatusDTO responseStatusDTO = mapper.readValue(response, ResponseStatusDTO.class);

        assertNotNull(responseStatusDTO);
        assertEquals(responseStatusDTO.getId(), createResponseStatusDTO().getId());
        verify(statusService, times(1)).save(createCreateStatusDTO());
    }

    @Test
    void findById_ShouldFindSTAndReturnResponseStatusDTO_WhenGivenExistingId() throws Exception {
        when(statusService.findById(ST_ID)).thenReturn(createResponseStatusDTO());
        MvcResult mvcResult = mockMvc.perform(get("/status/{id}", ST_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ResponseStatusDTO responseStatusDTO = mapper.readValue(response, ResponseStatusDTO.class);

        assertNotNull(responseStatusDTO);
        assertEquals(responseStatusDTO.getId(), createResponseStatusDTO().getId());
        verify(statusService, times(1)).findById(ST_ID);
    }

    @Test
    void findAll_ShouldReturnAllResponseStatusDTO_WhenStatusInDB() throws Exception {
        int page = 0;
        int size = 9;
        String query = "";
        Pageable pageable = PageRequest.of(page, size);

        when(statusService.findAllPageable(pageable, query)).thenReturn(createResponseStatusDTOPage());
        MvcResult mvcResult = mockMvc.perform(get("/status/all?page={page}&itemsPerPage={size}", page, size)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
//        String response = mvcResult.getResponse().getContentAsString();
//        ResponseStatusDTO[] responseStatusDTOS = mapper.readValue(response, ResponseStatusDTO[].class);
//
//        assertNotNull(responseStatusDTOS);
        verify(statusService, times(1)).findAllPageable(pageable, query);
    }

    private Status createStatus() {
        Status status = new Status();
        status.setId(ST_ID);
        status.setName(NAME);
        status.setDescription(DESCRIPTION);
        return status;
    }

    private UpdateStatusDTO createUpdateStatusDTO() {
        UpdateStatusDTO updateStatusDTO = new UpdateStatusDTO();
        updateStatusDTO.setName(NAME);
        updateStatusDTO.setDescription(DESCRIPTION);
        return updateStatusDTO;
    }

    private CreateStatusDTO createCreateStatusDTO() {
        CreateStatusDTO createStatusDTO = new CreateStatusDTO();
        createStatusDTO.setName(NAME);
        createStatusDTO.setDescription(DESCRIPTION);
        return createStatusDTO;
    }

    private ResponseStatusDTO createResponseStatusDTO() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setId(ST_ID);
        responseStatusDTO.setName(NAME);
        responseStatusDTO.setDescription(DESCRIPTION);
        responseStatusDTO.setCreatedAt(LocalDateTime.now());
        responseStatusDTO.setUpdatedAt(LocalDateTime.now());
        return responseStatusDTO;
    }

    private Page<ResponseStatusDTO> createResponseStatusDTOPage() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setId(1L);
        responseStatusDTO.setName("name 1");
        responseStatusDTO.setDescription("desc 1");
        responseStatusDTO.setCreatedAt(LocalDateTime.now());
        responseStatusDTO.setUpdatedAt(LocalDateTime.now());

        ResponseStatusDTO responseStatusDTO1 = new ResponseStatusDTO();
        responseStatusDTO.setId(2L);
        responseStatusDTO.setName("name 2");
        responseStatusDTO.setDescription("desc 2");
        responseStatusDTO.setCreatedAt(LocalDateTime.now());
        responseStatusDTO.setUpdatedAt(LocalDateTime.now());

        ResponseStatusDTO responseStatusDTO2 = new ResponseStatusDTO();
        responseStatusDTO.setId(3L);
        responseStatusDTO.setName("name 3");
        responseStatusDTO.setDescription("desc 3");
        responseStatusDTO.setCreatedAt(LocalDateTime.now());
        responseStatusDTO.setUpdatedAt(LocalDateTime.now());

        return new PageImpl<>(List.of(responseStatusDTO
                , responseStatusDTO1
                , responseStatusDTO2));
    }
}
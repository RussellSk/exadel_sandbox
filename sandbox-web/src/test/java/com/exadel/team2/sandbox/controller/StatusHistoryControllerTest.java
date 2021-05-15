package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.service.StatusHistoryService;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.status.ResponseStatusDTO;
import com.exadel.team2.sandbox.web.statushistory.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatusHistoryController.class)
@AutoConfigureMockMvc
class StatusHistoryControllerTest {

    private static final Long STH_ID = 1L;
    private static final Long ST_ID = 2L;
    private static final Long CN_ID = 3L;
    private static final Long EMP_ID = 4L;
    private static final String CHANGE = "change note";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusHistoryService statusHistoryService;


    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    static void setUp() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void update_ShouldReturnResponseSTHDTO_WhenSTHNotNull() throws Exception {
        String json = mapper.writeValueAsString(createUpdateStatusHistoryDTO());

        when(statusHistoryService.update(STH_ID, createUpdateStatusHistoryDTO())).thenReturn(createResponseStatusHistoryDTO());

        MvcResult mvcResult = mockMvc.perform(put("/status/history/{id}", STH_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ResponseStatusHistoryDTO dto = mapper.readValue(response, ResponseStatusHistoryDTO.class);

        assertNotNull(dto);
        assertEquals(dto.getId(), STH_ID);
        verify(statusHistoryService, times(1)).update(STH_ID, createUpdateStatusHistoryDTO());
    }

    @Test
    void save_ShouldReturnResponseSTHDTO_WhenSTHNotNull() throws Exception {
        String json = mapper.writeValueAsString(createCreateStatusHistoryDTO());

        when(statusHistoryService.save(createCreateStatusHistoryDTO())).thenReturn(createResponseStatusHistoryDTO());
        MvcResult mvcResult = mockMvc.perform(post("/status/history").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ResponseStatusHistoryDTO dto = mapper.readValue(response, ResponseStatusHistoryDTO.class);

        assertNotNull(dto);
        assertEquals(dto.getId(), createResponseStatusHistoryDTO().getId());
        verify(statusHistoryService, times(1)).save(createCreateStatusHistoryDTO());
    }

    @Test
    void findById_ShouldReturnResponseSTHDTO_WhenGivenExistingId() throws Exception {
        when(statusHistoryService.findById(STH_ID)).thenReturn(createResponseStatusHistoryDTO());
        MvcResult mvcResult = mockMvc.perform(get("/status/history/{id}", STH_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ResponseStatusHistoryDTO dto = mapper.readValue(response, ResponseStatusHistoryDTO.class);

        assertNotNull(dto);
        assertEquals(dto.getId(), createResponseStatusHistoryDTO().getId());
        verify(statusHistoryService, times(1)).findById(STH_ID);
    }

    @Test
    void findAll_ShouldReturnAllResponseSTHDTO_WhenSTHInDB() throws Exception {
        int page = 0;
        int size = 9;
        String query = "";
        Pageable pageable = PageRequest.of(page, size);

        when(statusHistoryService.findAllPageable(pageable, query)).thenReturn(createResponseStatusHistoryDTOPage());
        MvcResult mvcResult = mockMvc.perform(get("/status/history/all?page={page}&itemsPerPage={size}", page, size)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        verify(statusHistoryService, times(1)).findAllPageable(pageable, query);
    }


    private CreateStatusHistoryDTO createCreateStatusHistoryDTO() {
        CreateStatusHistoryDTO dto = new CreateStatusHistoryDTO();
        dto.setStatusId(ST_ID);
        dto.setCandidateId(CN_ID);
        dto.setEmployeeId(EMP_ID);
        dto.setChangeNote(CHANGE);
        return dto;
    }

    private UpdateStatusHistoryDTO createUpdateStatusHistoryDTO() {
        UpdateStatusHistoryDTO dto = new UpdateStatusHistoryDTO();
        dto.setStatusId(ST_ID);
        dto.setCandidateId(CN_ID);
        dto.setEmployeeId(EMP_ID);
        dto.setChangeNote(CHANGE);
        return dto;
    }

    private ResponseStatusHistoryDTO createResponseStatusHistoryDTO() {
        ResponseStatusHistoryDTO resp = new ResponseStatusHistoryDTO();
        resp.setId(STH_ID);
        resp.setStatus(new ResponseStatusDTO());
        resp.setCandidate(new CandidateResponseDTO());
        resp.setEmployee(new ResponseEmployeeDto());
        resp.setCreatedAt(LocalDateTime.now());
        resp.setUpdatedAt(LocalDateTime.now());
        return resp;
    }

    private Page<ResponseStatusHistoryDTO> createResponseStatusHistoryDTOPage() {

        ResponseStatusHistoryDTO resp = createResponseStatusHistoryDTO();
        resp.setId(1L);
        ResponseStatusHistoryDTO resp1 = createResponseStatusHistoryDTO();
        resp1.setId(2L);
        ResponseStatusHistoryDTO resp2 = createResponseStatusHistoryDTO();
        resp1.setId(3L);

        return new PageImpl<>(List.of(resp
                , resp1
                , resp2));
    }
}
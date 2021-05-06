package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.CandidateEventEntity;
import com.exadel.team2.sandbox.mapper.CandidateEventMapper;
import com.exadel.team2.sandbox.service.impl.CandidateEventServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CandidateEventControllerTest {

    @Autowired
    private CandidateEventMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CandidateEventServiceImpl service;

    @Test
    public void givenCE_whenAdd_thenStatus201andCE_Returned() throws Exception {
        CandidateEventEntity entity = new CandidateEventEntity(100L,null,null, LocalDateTime.now());
        mockMvc.perform(
                post("http://localhost:8085/api/candidate-event")
                        .content(objectMapper.convertEntityToDto(entity).toString())
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(100L))
                .andExpect(jsonPath("$.candidate").value(null))
                .andExpect(jsonPath("$.event").value(null))
                .andExpect(jsonPath("$.createdAt").value(LocalDateTime.now()));
    }

    @Test
    void getCandidateEvent() {

    }

    @Test
    void getAllCandidateEvent() {
    }

    @Test
    void createCandidateEvent() {
    }

    @Test
    void deleteCandidateEvent() {
    }
}
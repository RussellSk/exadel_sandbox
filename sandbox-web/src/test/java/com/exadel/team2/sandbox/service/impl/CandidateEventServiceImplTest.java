package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.CandidateDAO;
import com.exadel.team2.sandbox.dao.CandidateEventDAO;
import com.exadel.team2.sandbox.dao.EventDAO;
import com.exadel.team2.sandbox.entity.CandidateEntity;
import com.exadel.team2.sandbox.entity.CandidateEventEntity;
import com.exadel.team2.sandbox.entity.EventEntity;
import com.exadel.team2.sandbox.exceptions.NoSuchException;
import com.exadel.team2.sandbox.mapper.CandidateEventMapper;
import com.exadel.team2.sandbox.service.CandidateEventService;
import com.exadel.team2.sandbox.web.candidate_event.CreateCandidateEventDto;
import com.exadel.team2.sandbox.web.candidate_event.ResponseCandidateEventDto;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {CandidateEventServiceImpl.class, CandidateEventMapper.class})
class CandidateEventServiceImplTest {

    private static final Long CE_ID = 1L;
    private static final Long ID = 2L;

    @Autowired
    private CandidateEventService candidateEventService;

    @MockBean
    private EventDAO eventDAO;

    @MockBean
    private CandidateDAO candidateDAO;

    @MockBean
    private CandidateEventDAO candidateEventDAO;

    @Test
    void saveWhenCE_NotNull() {
        CandidateEventEntity candidateEventEntity = createOptional().get();
        candidateEventEntity.setId(null);
        when(candidateDAO.findById(ID)).thenReturn(Optional.of(candidateEventEntity.getCandidate()));
        when(eventDAO.findById(ID)).thenReturn(Optional.of(candidateEventEntity.getEvent()));
        when(candidateEventDAO.save(candidateEventEntity)).thenReturn(candidateEventEntity);
        ResponseCandidateEventDto responseCandidateEventDto = candidateEventService.save(createCandidateEventDTO());
        assertNotNull(responseCandidateEventDto);
        verify(candidateDAO, times(1)).findById(ID);
        verify(eventDAO, times(1)).findById(ID);
        verify(candidateEventDAO, times(1)).save(candidateEventEntity);
    }

    @Test
    void save_ShouldThrowException_WhenCE_IsNull() {
        when(candidateEventDAO.save(null)).thenReturn(null);
        assertThrows(Exception.class, () -> candidateEventService.save(null));
    }

    @Test
    void findById_ShouldFindCE_WhenGivenExistingId() {
        when(candidateEventDAO.findById(ArgumentMatchers.eq(CE_ID))).thenReturn(createOptional());
        ResponseCandidateEventDto resp = candidateEventService.getById(CE_ID);
        assertNotNull(resp);
        assertEquals(CE_ID, resp.getId());
        verify(candidateEventDAO, times(1)).findById(ArgumentMatchers.eq(CE_ID));
    }

    @Test
    void findById_ShouldThrowException_WhenGivenNotExistingId() {
        when(candidateEventDAO.findById(ArgumentMatchers.eq(CE_ID))).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> candidateEventService.getById(CE_ID));
        verify(candidateEventDAO, times(1)).findById(ArgumentMatchers.eq(CE_ID));
    }

    @Test
    void deleteById_ShouldDeleteIFB_ById_WhenGivenExistingId() {
        when(candidateEventDAO.existsById(CE_ID)).thenReturn(true);
        candidateEventService.delete(CE_ID);
        verify(candidateEventDAO, times(1)).deleteById(CE_ID);
    }

    @Test
    void deleteById_ShouldThrowException_WhenGivenNotExistingId() {
        when(candidateEventDAO.existsById(CE_ID)).thenReturn(false);
        assertThrows(NoSuchException.class, () -> candidateEventService.delete(CE_ID));
    }

    private CreateCandidateEventDto createCandidateEventDTO() {
        CreateCandidateEventDto createCandidateEventDto = new CreateCandidateEventDto();
        createCandidateEventDto.setIdCandidate(ID);
        createCandidateEventDto.setIdEvent(ID);
        return createCandidateEventDto;
    }

    private Optional<CandidateEventEntity> createOptional() {
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setId(ID);
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(ID);
        CandidateEventEntity entity = new CandidateEventEntity();
        entity.setId(CE_ID);
        entity.setEvent(eventEntity);
        entity.setCandidate(candidateEntity);
        return Optional.of(entity);
    }
}
package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.BaseTestClass;
import com.exadel.team2.sandbox.dao.CandidateDAO;
import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.dao.StatusDAO;
import com.exadel.team2.sandbox.dao.StatusHistoryDAO;
import com.exadel.team2.sandbox.entity.CandidateEntity;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.Status;
import com.exadel.team2.sandbox.entity.StatusHistory;
import com.exadel.team2.sandbox.service.StatusHistoryService;
import com.exadel.team2.sandbox.web.statushistory.CreateStatusHistoryDTO;
import com.exadel.team2.sandbox.web.statushistory.ResponseStatusHistoryDTO;
import com.exadel.team2.sandbox.web.statushistory.UpdateStatusHistoryDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatusHistoryServiceImplTest extends BaseTestClass {

    private static final Long STH_ID = 1L;
    private static final Long ID = 2L;
    @Autowired
    private StatusHistoryService historyService;

    @MockBean
    private EmployeeDAO employeeDAO;

    @MockBean
    private CandidateDAO candidateDAO;

    @MockBean
    private StatusHistoryDAO statusHistoryDAO;

    @MockBean
    private StatusDAO statusDAO;

    @Test
    void save_ShouldSaveStatusHistory_WhenStatusHistoryNotNull() {
        StatusHistory statusHistory = createOptionalStatusHistory().get();
        statusHistory.setId(null);

        when(statusDAO.findById(ID)).thenReturn(Optional.of(statusHistory.getStatus()));
        when(candidateDAO.findById(ID)).thenReturn(Optional.of(statusHistory.getCandidate()));
        when(employeeDAO.findById(ID)).thenReturn(Optional.of(statusHistory.getEmployee()));
        when(statusHistoryDAO.save(statusHistory)).thenReturn(statusHistory);

        ResponseStatusHistoryDTO responseStatusHistoryDTO = historyService.save(createCreateStatusHistoryDTO());

        assertNotNull(responseStatusHistoryDTO);
        verify(statusDAO, times(1)).findById(ID);
        verify(candidateDAO, times(1)).findById(ID);
        verify(employeeDAO, times(1)).findById(ID);
        verify(statusHistoryDAO, times(1)).save(statusHistory);
    }

    @Test
    void save_ShouldThrowException_WhenStatusHistoryIsNull() {
        when(statusHistoryDAO.save(null)).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> historyService.save(null));
    }
//
//    @Test
//    void update() {
//        StatusHistory statusHistory = createOptionalStatusHistory().get();
//        statusHistory.setId(ID);
//        when(statusHistoryDAO.existsById(ID)).thenReturn(true);
//        when(statusDAO.findById(ID)).thenReturn(Optional.of(statusHistory.getStatus()));
//        when(candidateDAO.findById(ID)).thenReturn(Optional.of(statusHistory.getCandidate()));
//        when(employeeDAO.findById(ID)).thenReturn(Optional.of(statusHistory.getEmployee()));
//        when(statusHistoryDAO.save(statusHistory)).thenReturn(statusHistory);
//
//        ResponseStatusHistoryDTO update = historyService.update(ID, createUpdateStatusHistoryDTO());
//    }

    @Test
    void findById_ShouldFindStatusHistory_WhenGivenExistingId() {

        when(statusHistoryDAO.findById(ArgumentMatchers.eq(STH_ID))).thenReturn(createOptionalStatusHistory());

        ResponseStatusHistoryDTO resp = historyService.findById(STH_ID);

        assertNotNull(resp);
        assertEquals(STH_ID, resp.getId());

        verify(statusHistoryDAO, times(1)).findById(ArgumentMatchers.eq(STH_ID));
    }

    @Test
    void findById_ShouldThrowException_WhenGivenNotExistingId() {

        when(statusHistoryDAO.findById(ArgumentMatchers.eq(STH_ID))).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> historyService.findById(STH_ID));

        verify(statusHistoryDAO, times(1)).findById(ArgumentMatchers.eq(STH_ID));
    }

    @Test
    void findAll_ShouldFindAllStatusHistory_WhenStatusHistoryExistingInDb() {
        when(statusHistoryDAO.findAll()).thenReturn(createStatusList());

        List<ResponseStatusHistoryDTO> dtoList = historyService.findAll();

        assertNotNull(dtoList);
        assertEquals(createResponseStatusHistoryDTOList(), dtoList);

        verify(statusHistoryDAO, times(1)).findAll();

    }

    @Test
    void findAll_ShouldThrowException_WhenStatusHistoryNotExistingInDb() {
        when(statusHistoryDAO.findAll()).thenReturn(createEmptyStatusList());

        assertThrows(ResponseStatusException.class, () -> historyService.findAll());
    }

    @Test
    void deleteById_ShouldDeleteStatusHistoryById_WhenGivenExistingId() {
        when(statusHistoryDAO.existsById(STH_ID)).thenReturn(true);

        historyService.deleteById(STH_ID);

        verify(statusHistoryDAO, times(1)).deleteById(STH_ID);
    }

    @Test
    void deleteById_ShouldThrowException_WhenGivenNotExistingId() {
        when(statusHistoryDAO.existsById(STH_ID)).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> historyService.deleteById(STH_ID));
    }

    private List<StatusHistory> createStatusList() {
        StatusHistory statusHistory = new StatusHistory();
        statusHistory.setId(STH_ID);
        List<StatusHistory> list = new ArrayList<>();
        list.add(statusHistory);
        return list;
    }

    private List<StatusHistory> createEmptyStatusList() {
        return new ArrayList<>();
    }


    private List<ResponseStatusHistoryDTO> createResponseStatusHistoryDTOList() {
        ResponseStatusHistoryDTO responseStatusHistoryDTO = new ResponseStatusHistoryDTO();
        responseStatusHistoryDTO.setId(STH_ID);
        List<ResponseStatusHistoryDTO> list = new ArrayList<>();
        list.add(responseStatusHistoryDTO);
        return list;
    }

    private UpdateStatusHistoryDTO createUpdateStatusHistoryDTO() {
        UpdateStatusHistoryDTO updateStatusHistoryDTO = new UpdateStatusHistoryDTO();
        updateStatusHistoryDTO.setStatusId(ID);
        updateStatusHistoryDTO.setCandidateId(ID);
        updateStatusHistoryDTO.setEmployeeId(ID);
        return updateStatusHistoryDTO;
    }

    private CreateStatusHistoryDTO createCreateStatusHistoryDTO() {
        CreateStatusHistoryDTO createStatusHistoryDTO = new CreateStatusHistoryDTO();
        createStatusHistoryDTO.setStatusId(ID);
        createStatusHistoryDTO.setCandidateId(ID);
        createStatusHistoryDTO.setEmployeeId(ID);
        return createStatusHistoryDTO;
    }

    private Optional<StatusHistory> createOptionalStatusHistory() {
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setId(ID);

        Status status = new Status();
        status.setId(ID);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(ID);

        StatusHistory statusHistory = new StatusHistory();
        statusHistory.setId(STH_ID);
        statusHistory.setStatus(status);
        statusHistory.setEmployee(employeeEntity);
        statusHistory.setCandidate(candidateEntity);
        return Optional.of(statusHistory);
    }
}
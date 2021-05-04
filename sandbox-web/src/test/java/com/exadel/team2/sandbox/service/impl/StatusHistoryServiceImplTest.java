//package com.exadel.team2.sandbox.service.impl;
//
//import com.exadel.team2.sandbox.BaseTestClass;
//import com.exadel.team2.sandbox.dao.EmployeeDAO;
//import com.exadel.team2.sandbox.dao.StatusDAO;
//import com.exadel.team2.sandbox.dao.StatusHistoryDAO;
//import com.exadel.team2.sandbox.entity.StatusHistory;
//import com.exadel.team2.sandbox.service.StatusHistoryService;
//import com.exadel.team2.sandbox.web.statushistory.ResponseStatusHistoryDTO;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class StatusHistoryServiceImplTest extends BaseTestClass {
//
//    private static final Long STH_ID = 1L;
//    @Autowired
//    private StatusHistoryService historyService;
//
//    @MockBean
//    private EmployeeDAO employeeDAO;
//
//    @MockBean
//    private StatusHistoryDAO statusHistoryDAO;
//
//    @MockBean
//    private StatusDAO statusDAO;
//
//    @Test
//    void findById_ShouldFindStatusHistory_WhenGivenExistingId() {
//
//        when(statusHistoryDAO.findById(ArgumentMatchers.eq(STH_ID))).thenReturn(createOptionalStatusHistory());
//
//        ResponseStatusHistoryDTO resp = historyService.findById(STH_ID);
//
//        assertNotNull(resp);
//        assertEquals(STH_ID, resp.getId());
//
//        verify(statusHistoryDAO, times(1)).findById(ArgumentMatchers.eq(STH_ID));
//    }
//
//    @Test
//    void findById_ShouldThrowException_WhenGivenNotExistingId() {
//
//        when(statusHistoryDAO.findById(ArgumentMatchers.eq(STH_ID))).thenReturn(Optional.empty());
//
//        assertThrows(ResponseStatusException.class, () -> historyService.findById(STH_ID));
//
//        verify(statusHistoryDAO, times(1)).findById(ArgumentMatchers.eq(STH_ID));
//    }
//
//    @Test
//    void findAll_ShouldFindAllStatusHistory_WhenStatusHistoryExistingInDb() {
//        when(statusHistoryDAO.findAll()).thenReturn(createStatusList());
//
//        List<ResponseStatusHistoryDTO> dtoList = historyService.findAll();
//
//        assertNotNull(dtoList);
//        assertEquals(createResponseStatusHistoryDTOList(), dtoList);
//
//        verify(statusHistoryDAO, times(1)).findAll();
//
//    }
//
//    @Test
//    void findAll_ShouldThrowException_WhenStatusHistoryNotExistingInDb() {
//        when(statusHistoryDAO.findAll()).thenReturn(createEmptyStatusList());
//
//        assertThrows(ResponseStatusException.class, () -> historyService.findAll());
//    }
//
//    @Test
//    void deleteById_ShouldDeleteStatusHistoryById_WhenGivenExistingId() {
//        when(statusHistoryDAO.existsById(STH_ID)).thenReturn(true);
//
//        historyService.deleteById(STH_ID);
//
//        verify(statusHistoryDAO, times(1)).deleteById(STH_ID);
//    }
//
//    @Test
//    void deleteById_ShouldThrowException_WhenGivenNotExistingId() {
//        when(statusHistoryDAO.existsById(STH_ID)).thenReturn(false);
//        assertThrows(ResponseStatusException.class, () -> historyService.deleteById(STH_ID));
//    }
//
//    private List<StatusHistory> createStatusList() {
//        StatusHistory statusHistory = new StatusHistory();
//        statusHistory.setId(STH_ID);
//        List<StatusHistory> list = new ArrayList<>();
//        list.add(statusHistory);
//        return list;
//    }
//
//    private List<StatusHistory> createEmptyStatusList() {
//        return new ArrayList<>();
//    }
//
//
//    private List<ResponseStatusHistoryDTO> createResponseStatusHistoryDTOList() {
//        ResponseStatusHistoryDTO responseStatusHistoryDTO = new ResponseStatusHistoryDTO();
//        responseStatusHistoryDTO.setId(STH_ID);
//        List<ResponseStatusHistoryDTO> list = new ArrayList<>();
//        list.add(responseStatusHistoryDTO);
//        return list;
//    }
//
//    private Optional<StatusHistory> createOptionalStatusHistory() {
//        StatusHistory statusHistory = new StatusHistory();
//        statusHistory.setId(STH_ID);
//        return Optional.of(statusHistory);
//    }
//}
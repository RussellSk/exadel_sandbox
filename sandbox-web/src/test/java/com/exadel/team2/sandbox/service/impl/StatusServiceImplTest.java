//package com.exadel.team2.sandbox.service.impl;
//
//import com.exadel.team2.sandbox.BaseTestClass;
//import com.exadel.team2.sandbox.dao.StatusDAO;
//import com.exadel.team2.sandbox.entity.Status;
//import com.exadel.team2.sandbox.service.StatusService;
//import com.exadel.team2.sandbox.web.status.CreateStatusDTO;
//import com.exadel.team2.sandbox.web.status.ResponseStatusDTO;
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
//class StatusServiceImplTest extends BaseTestClass {
//
//    private static final Long STATUS_ID = 3L;
//
//    @Autowired
//    private StatusService statusService;
//
//    @MockBean
//    private StatusDAO statusDAO;
//
//    @Test
//    void findById_ShouldFindStatus_WhenGivenExistingId() {
//
//        when(statusDAO.findById(STATUS_ID)).thenReturn(createOptionalStatus());
//
//        ResponseStatusDTO responseStatusDTO = statusService.findById(STATUS_ID);
//
//        assertNotNull(responseStatusDTO);
//        assertEquals(STATUS_ID, responseStatusDTO.getId());
//
//        verify(statusDAO, times(1)).findById(ArgumentMatchers.eq(STATUS_ID));
//    }
//
//    @Test
//    void findById_ShouldThrowException_WhenGivenNotExistingId() {
//
//        when(statusDAO.findById(STATUS_ID)).thenReturn(Optional.empty());
//
//        assertThrows(ResponseStatusException.class, () -> statusService.findById(STATUS_ID));
//
//        verify(statusDAO, times(1)).findById(STATUS_ID);
//    }
//
//    @Test
//    void findAll_ShouldFindAllStatus_WhenStatusExistingInDb() {
//        when(statusDAO.findAll()).thenReturn(createStatusList());
//
//        List<ResponseStatusDTO> dtoList = statusService.findAll();
//
//        assertNotNull(dtoList);
//        assertEquals(createResponseStatusDTOList(), dtoList);
//
//        verify(statusDAO, times(1)).findAll();
//
//    }
//
//    @Test
//    void findAll_ShouldThrowException_WhenStatusNotExistingInDb() {
//        when(statusDAO.findAll()).thenReturn(createEmptyStatusList());
//
//        assertThrows(ResponseStatusException.class, () -> statusService.findAll());
//    }
//
//    @Test
//    void deleteById_ShouldDeleteStatusById_WhenGivenExistingId() {
//        when(statusDAO.existsById(STATUS_ID)).thenReturn(true);
//
//        statusService.deleteById(STATUS_ID);
//
//        verify(statusDAO, times(1)).deleteById(STATUS_ID);
//    }
//
//    @Test
//    void deleteById_ShouldThrowException_WhenGivenNotExistingId() {
//        when(statusDAO.existsById(STATUS_ID)).thenReturn(false);
//        assertThrows(ResponseStatusException.class, () -> statusService.deleteById(STATUS_ID));
//    }
//
//    private List<Status> createStatusList() {
//        Status status = new Status();
//        status.setId(STATUS_ID);
//        List<Status> list = new ArrayList<>();
//        list.add(status);
//        return list;
//    }
//
//    private List<Status> createEmptyStatusList() {
//        return new ArrayList<>();
//    }
//
//    private List<ResponseStatusDTO> createResponseStatusDTOList() {
//        ResponseStatusDTO resp = new ResponseStatusDTO();
//        resp.setId(STATUS_ID);
//        List<ResponseStatusDTO> list = new ArrayList<>();
//        list.add(resp);
//        return list;
//    }
//
//    private Optional<Status> createOptionalStatus() {
//        Status status = new Status();
//        status.setId(STATUS_ID);
//        status.setName("Some status name");
//        status.setDescription("some desc");
//        return Optional.of(status);
//    }
//
//    private ResponseStatusDTO createResponseStatusDTO() {
//        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
//        responseStatusDTO.setId(STATUS_ID);
//        return responseStatusDTO;
//    }
//
//    private CreateStatusDTO creatCreateStatusDTO() {
//        CreateStatusDTO createStatusDTO = new CreateStatusDTO();
//        createStatusDTO.setName("Some status name");
//        createStatusDTO.setDescription("some desc");
//        return createStatusDTO;
//    }
//
//}
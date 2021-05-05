package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.BaseTestClass;
import com.exadel.team2.sandbox.dao.StatusDAO;
import com.exadel.team2.sandbox.entity.Status;
import com.exadel.team2.sandbox.mapper.StatusMapperDTO;
import com.exadel.team2.sandbox.service.StatusService;
import com.exadel.team2.sandbox.web.status.CreateStatusDTO;
import com.exadel.team2.sandbox.web.status.ResponseStatusDTO;
import com.exadel.team2.sandbox.web.status.UpdateStatusDTO;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {StatusServiceImpl.class, StatusMapperDTO.class})
class StatusServiceImplTest extends BaseTestClass {

    private static final Long STATUS_ID = 3L;
    private static final String NAME = "Some name";
    private static final String DESCRIPTION = "Some desc";

    @Autowired
    private StatusService statusService;

    @MockBean
    private StatusDAO statusDAO;


    @Test
    void save_ShouldSaveStatus_WhenStatusNotNull() {
        Status status = createOptionalStatus().get();
        status.setId(null);

        when(statusDAO.save(status)).thenReturn(status);

        ResponseStatusDTO responseStatusDTO = statusService.save(creatCreateStatusDTO());

        assertNotNull(responseStatusDTO);
        verify(statusDAO, times(1)).save(status);
    }

    @Test
    void save_ShouldThrowException_WhenStatusIsNull() {
        when(statusDAO.save(null)).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> statusService.save(null));
    }

    @Test
    void update_ShouldUpdateStatus_WhenGivenExistingId() {
        Status status = createOptionalStatus().get();
        when(statusDAO.existsById(STATUS_ID)).thenReturn(true);
        when(statusDAO.save(status)).thenReturn(status);

        ResponseStatusDTO responseStatusDTO = statusService.update(STATUS_ID, createUpdateStatusDTO());

        assertNotNull(responseStatusDTO);
        assertEquals(STATUS_ID, responseStatusDTO.getId());
        verify(statusDAO, times(1)).save(status);
    }

//    @Test
//    void update_ShouldThrowException_WhenGivenNotExistingId() {
//        when(statusDAO.existsById(STATUS_ID)).thenReturn(false);
//        assertThrows(ResponseStatusException.class, () -> statusService.update(STATUS_ID, createUpdateStatusDTO()));
//        verify(statusDAO, times(1)).existsById(STATUS_ID);
//    }

    @Test
    void findById_ShouldFindStatus_WhenGivenExistingId() {

        when(statusDAO.findById(STATUS_ID)).thenReturn(createOptionalStatus());

        ResponseStatusDTO responseStatusDTO = statusService.findById(STATUS_ID);

        assertNotNull(responseStatusDTO);
        assertEquals(STATUS_ID, responseStatusDTO.getId());

        verify(statusDAO, times(1)).findById(ArgumentMatchers.eq(STATUS_ID));
    }

    @Test
    void findById_ShouldThrowException_WhenGivenNotExistingId() {

        when(statusDAO.findById(STATUS_ID)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> statusService.findById(STATUS_ID));

        verify(statusDAO, times(1)).findById(STATUS_ID);
    }

    @Test
    void findAll_ShouldFindAllStatus_WhenStatusExistingInDb() {
        when(statusDAO.findAll()).thenReturn(createStatusList());

        List<ResponseStatusDTO> dtoList = statusService.findAll();

        assertNotNull(dtoList);
        assertEquals(createResponseStatusDTOList(), dtoList);

        verify(statusDAO, times(1)).findAll();

    }

    @Test
    void findAll_ShouldThrowException_WhenStatusNotExistingInDb() {
        when(statusDAO.findAll()).thenReturn(createEmptyStatusList());

        assertThrows(ResponseStatusException.class, () -> statusService.findAll());
    }

    @Test
    void deleteById_ShouldDeleteStatusById_WhenGivenExistingId() {
        when(statusDAO.existsById(STATUS_ID)).thenReturn(true);

        statusService.deleteById(STATUS_ID);

        verify(statusDAO, times(1)).deleteById(STATUS_ID);
    }

    @Test
    void deleteById_ShouldThrowException_WhenGivenNotExistingId() {
        when(statusDAO.existsById(STATUS_ID)).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> statusService.deleteById(STATUS_ID));
    }

    private List<Status> createStatusList() {
        Status status = new Status();
        status.setId(STATUS_ID);
        List<Status> list = new ArrayList<>();
        list.add(status);
        return list;
    }

    private List<Status> createEmptyStatusList() {
        return new ArrayList<>();
    }

    private List<ResponseStatusDTO> createResponseStatusDTOList() {
        ResponseStatusDTO resp = new ResponseStatusDTO();
        resp.setId(STATUS_ID);
        List<ResponseStatusDTO> list = new ArrayList<>();
        list.add(resp);
        return list;
    }

    private UpdateStatusDTO createUpdateStatusDTO() {
        UpdateStatusDTO updateStatusDTO = new UpdateStatusDTO();
        updateStatusDTO.setName(NAME);
        updateStatusDTO.setDescription(DESCRIPTION);
        return updateStatusDTO;
    }

    private Optional<Status> createOptionalStatus() {
        Status status = new Status();
        status.setId(STATUS_ID);
        status.setName(NAME);
        status.setDescription(DESCRIPTION);
        return Optional.of(status);
    }


    private CreateStatusDTO creatCreateStatusDTO() {
        CreateStatusDTO createStatusDTO = new CreateStatusDTO();
        createStatusDTO.setName(NAME);
        createStatusDTO.setDescription(DESCRIPTION);
        return createStatusDTO;
    }

}
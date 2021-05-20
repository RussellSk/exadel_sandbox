package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.service.impl.EmployeeAvailabilityTimeServiceImpl;
import com.exadel.team2.sandbox.service.impl.EmployeeServiceImpl;
import com.exadel.team2.sandbox.web.employee.CreateEmployeeDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.employee.UpdateEmployeeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {EmployeeController.class})
public class EmployeeControllerTest {

    private final Long EMP_ID = 1L;
    private final Long RL_ID = 1L;
    private final String EMP_EMAIL = "testEmployee@exadel.com";

    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @MockBean
    private EmployeeAvailabilityTimeServiceImpl employeeAvailabilityTimeService;

    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        employeeService = mock(EmployeeServiceImpl.class);
        employeeAvailabilityTimeService = mock(EmployeeAvailabilityTimeServiceImpl.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeController(employeeService, employeeAvailabilityTimeService)).build();
    }

    @Test
    void employeeController_findById() throws Exception {
        String json = mapper.writeValueAsString(createEmployeeDto());
        mapper.registerModule(new JavaTimeModule());
        when(employeeService.getById(EMP_ID)).thenReturn(createResponseEmployeeDto());

        MvcResult mvcResult = mockMvc.perform(get("/employees/{id}", EMP_ID)
            .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ResponseEmployeeDto responseEmployeeDto = mapper.readValue(response, ResponseEmployeeDto.class);

        assertNotNull(responseEmployeeDto);
        assertEquals(EMP_ID, responseEmployeeDto.getId());
    }

    @Test
    void employeeController_create() throws Exception {
        String json = mapper.writeValueAsString(createEmployeeDto());
        mapper.registerModule(new JavaTimeModule());
        when(employeeService.save(createEmployeeDto())).thenReturn(createResponseEmployeeDto());

        MvcResult mvcResult = mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ResponseEmployeeDto responseEmployeeDto = mapper.readValue(response, ResponseEmployeeDto.class);

        assertNotNull(responseEmployeeDto);
        assertEquals(RL_ID, responseEmployeeDto.getId());
    }

    @Test
    void employeeController_update() throws Exception {
        String json = mapper.writeValueAsString(createUpdateEmployeeDto());
        mapper.registerModule(new JavaTimeModule());
        when(employeeService.update(EMP_ID, createUpdateEmployeeDto())).thenReturn(createResponseEmployeeDto());

        MvcResult mvcResult = mockMvc.perform(put("/employees/{id}", EMP_ID)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ResponseEmployeeDto responseEmployeeDto = mapper.readValue(response, ResponseEmployeeDto.class);

        assertNotNull(responseEmployeeDto);
        assertEquals(EMP_EMAIL, responseEmployeeDto.getEmail());
    }

    private EmployeeEntity createEmployeeEntity() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(EMP_ID);
        employeeEntity.setEmail("testEmployee@exadel.com");
        employeeEntity.setFirstName("Human");
        employeeEntity.setLastName("Humanovich");
        employeeEntity.setPassword("123456");
        employeeEntity.setPhone("+9989123455123");
        employeeEntity.setRole(createRoleEntity());
        employeeEntity.setCreatedAt(LocalDateTime.of(2021, 5, 20, 12, 35));
        employeeEntity.setUpdatedAt(LocalDateTime.of(2021, 5, 20, 12, 35));
        return employeeEntity;
    }

    private RoleEntity createRoleEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(RL_ID);
        roleEntity.setName("ADMIN_ROLE");
        roleEntity.setDescription("Administrators role");
        roleEntity.setCreatedAt(LocalDateTime.of(2021, 5, 20, 12, 35));
        roleEntity.setUpdatedAt(LocalDateTime.of(2021, 5, 20, 12, 35));
        return roleEntity;
    }

    private CreateEmployeeDto createEmployeeDto() {
        EmployeeEntity employeeEntity = createEmployeeEntity();
        CreateEmployeeDto createEmployeeDto = new CreateEmployeeDto();
        createEmployeeDto.setEmail(employeeEntity.getEmail());
        createEmployeeDto.setFirstName(employeeEntity.getFirstName());
        createEmployeeDto.setLastName(employeeEntity.getLastName());
        createEmployeeDto.setPassword(employeeEntity.getPassword());
        createEmployeeDto.setPhone(employeeEntity.getPhone());
        createEmployeeDto.setRoleId(employeeEntity.getRole().getId());
        return createEmployeeDto;
    }

    private ResponseEmployeeDto createResponseEmployeeDto() {
        EmployeeEntity employeeEntity = createEmployeeEntity();
        ResponseEmployeeDto responseEmployeeDto = new ResponseEmployeeDto();
        responseEmployeeDto.setId(EMP_ID);
        responseEmployeeDto.setEmail(EMP_EMAIL);
        responseEmployeeDto.setFirstName(employeeEntity.getFirstName());
        responseEmployeeDto.setLastName(employeeEntity.getLastName());
        responseEmployeeDto.setPhone(employeeEntity.getPhone());
        responseEmployeeDto.setCreatedAt(employeeEntity.getCreatedAt());
        responseEmployeeDto.setUpdatedAt(employeeEntity.getUpdatedAt());
        return responseEmployeeDto;
    }

    private UpdateEmployeeDto createUpdateEmployeeDto() {
        CreateEmployeeDto createEmployeeDto = createEmployeeDto();
        UpdateEmployeeDto updateEmployeeDto = new UpdateEmployeeDto();
        updateEmployeeDto.setEmail(createEmployeeDto.getEmail());
        updateEmployeeDto.setFirstName(createEmployeeDto.getFirstName());
        updateEmployeeDto.setLastName(createEmployeeDto.getLastName());
        updateEmployeeDto.setPhone(createEmployeeDto.getPhone());
        return updateEmployeeDto;
    }
}

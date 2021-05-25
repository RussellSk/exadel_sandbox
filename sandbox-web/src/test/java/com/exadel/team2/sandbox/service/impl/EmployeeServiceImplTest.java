package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.BaseTestClass;
import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.dao.RoleDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.mapper.EmployeeMapper;
import com.exadel.team2.sandbox.web.employee.CreateEmployeeDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {EmployeeServiceImpl.class})
public class EmployeeServiceImplTest extends BaseTestClass {

    private final Long EMP_ID = 1L;
    private final Long RL_ID = 1L;
    private final String EMP_EMAIL = "testEmployee@exadel.com";

    @MockBean
    private EmployeeDAO employeeDAO;

    @MockBean
    private RoleDAO roleDAO;

    @MockBean
    private EmployeeMapper employeeMapper;

    @MockBean
    private CandidateAvailabilityTimeServiceImpl candidateTimeService;

    @MockBean
    private EmployeeAvailabilityTimeServiceImpl employeeTimeService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void beforeEach() {
        employeeDAO = mock(EmployeeDAO.class);
        roleDAO = mock(RoleDAO.class);
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        candidateTimeService = mock(CandidateAvailabilityTimeServiceImpl.class);
        employeeTimeService = mock(EmployeeAvailabilityTimeServiceImpl.class);
        employeeMapper = mock(EmployeeMapper.class);
        employeeService = new EmployeeServiceImpl(employeeDAO, roleDAO, employeeMapper,
                candidateTimeService, employeeTimeService, bCryptPasswordEncoder);
    }

    @Test
    void saveEmployee() {
        EmployeeEntity employeeEntity = createEmployeeEntity();
        employeeEntity.setId(null);

        when(roleDAO.findById(RL_ID)).thenReturn(Optional.of(createRoleEntity()));
        when(bCryptPasswordEncoder.encode(employeeEntity.getPassword())).thenReturn("hash1234567");
        when(employeeMapper.convertDtoToEntity(createEmployeeDto())).thenReturn(employeeEntity);
        when(employeeMapper.convertEntityToDto(employeeEntity)).thenReturn(createResponseEmployeeDto());
        when(employeeDAO.save(employeeEntity)).thenReturn(employeeEntity);

        ResponseEmployeeDto responseEmployeeDto = employeeService.save(createEmployeeDto());
        assertNotNull(responseEmployeeDto);

        assertEquals(EMP_EMAIL, responseEmployeeDto.getEmail());
        assertEquals(EMP_ID, responseEmployeeDto.getId());
    }

    @Test
    void getEmployeeById() {
        EmployeeEntity employeeEntity = createEmployeeEntity();
        lenient().when(employeeMapper.convertDtoToEntity(createEmployeeDto())).thenReturn(employeeEntity);
        lenient().when(employeeMapper.convertEntityToDto(employeeEntity)).thenReturn(createResponseEmployeeDto());
        lenient().when(employeeDAO.findById(EMP_ID)).thenReturn(Optional.of(createEmployeeEntity()));

        ResponseEmployeeDto responseEmployeeDto = employeeService.getById(EMP_ID);

        assertNotNull(responseEmployeeDto);
        assertEquals(EMP_ID, responseEmployeeDto.getId());
        assertEquals(EMP_EMAIL, responseEmployeeDto.getEmail());
    }

    @Test
    void loadUserByEmail() {
        EmployeeEntity employeeEntity = createEmployeeEntity();
        lenient().when(employeeMapper.convertDtoToEntity(createEmployeeDto())).thenReturn(employeeEntity);
        lenient().when(employeeMapper.convertEntityToDto(employeeEntity)).thenReturn(createResponseEmployeeDto());
        lenient().when(employeeDAO.findByEmail(employeeEntity.getEmail())).thenReturn(employeeEntity);

        UserDetails user = employeeService.loadUserByUsername(employeeEntity.getEmail());

        assertNotNull(user);
        assertEquals(user.getUsername(), employeeEntity.getEmail());
    }

    @Test
    void getAllEmployees() {
        EmployeeEntity employeeEntity = createEmployeeEntity();
        lenient().when(employeeDAO.findAll()).thenReturn(Collections.singletonList(employeeEntity));

        List<ResponseEmployeeDto> employeeEntities = employeeService.getAll();

        assertNotNull(employeeEntities);
        assertNotEquals(employeeEntities.size(), 0);
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
}

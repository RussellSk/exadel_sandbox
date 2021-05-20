package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.BaseTestClass;
import com.exadel.team2.sandbox.dao.PermissionDAO;
import com.exadel.team2.sandbox.dao.RoleDAO;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.mapper.RoleMapper;
import com.exadel.team2.sandbox.service.PermissionService;
import com.exadel.team2.sandbox.web.role.ResponseRoleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {RoleServiceImpl.class, RoleMapper.class})
public class RoleServiceImplTest extends BaseTestClass {

    private final Long RL_ID = 1L;
    private final String RL_NAME = "ADMIN_ROLE";

    @MockBean
    private RoleMapper roleMapper;

    @MockBean
    private RoleDAO roleDAO;

    @MockBean
    private PermissionDAO permissionDAO;

    @MockBean
    private PermissionService permissionService;

    private RoleServiceImpl roleService;

    @BeforeEach
    public void beforeEach() {
        roleMapper = mock(RoleMapper.class);
        roleDAO = mock(RoleDAO.class);
        permissionDAO = mock(PermissionDAO.class);
        permissionService = mock(PermissionServiceImpl.class);
        roleService = new RoleServiceImpl(roleDAO, permissionService, roleMapper);
    }

    @Test
    void getRoleById() {
        lenient().when(roleDAO.findById(RL_ID)).thenReturn(Optional.of(createRoleEntity()));
        lenient().when(roleMapper.convertEntityToDto(createRoleEntity())).thenReturn(createResponseRoleDto());
        ResponseRoleDto responseRoleDto = roleService.getById(RL_ID);

        assertNotNull(responseRoleDto);
        assertEquals(responseRoleDto.getId(), RL_ID);
    }

    @Test
    void  getAllRoles() {
        RoleEntity roleEntity = createRoleEntity();
        lenient().when(roleDAO.findAll()).thenReturn(Collections.singletonList(roleEntity));

        List<ResponseRoleDto> responseRoleDtoList = roleService.getAll();

        assertNotNull(responseRoleDtoList);
        assertNotEquals(responseRoleDtoList.size(), 0);
    }

    private RoleEntity createRoleEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(RL_ID);
        roleEntity.setName(RL_NAME);
        roleEntity.setDescription("Administrators role");
        roleEntity.setCreatedAt(LocalDateTime.of(2021, 5, 20, 12, 35));
        roleEntity.setUpdatedAt(LocalDateTime.of(2021, 5, 20, 12, 35));
        return roleEntity;
    }

    private ResponseRoleDto createResponseRoleDto() {
        RoleEntity roleEntity = createRoleEntity();
        ResponseRoleDto responseRoleDto = new ResponseRoleDto();
        responseRoleDto.setId(roleEntity.getId());
        responseRoleDto.setDescription(roleEntity.getDescription());
        responseRoleDto.setName(roleEntity.getName());
        responseRoleDto.setPermissions(new ArrayList<>());
        return responseRoleDto;
    }
}

package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.RoleDAO;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.mapper.RoleMapper;
import com.exadel.team2.sandbox.service.PermissionService;
import com.exadel.team2.sandbox.service.RoleService;
import com.exadel.team2.sandbox.web.role.CreateRoleDto;
import com.exadel.team2.sandbox.web.role.ResponseRoleDto;
import com.exadel.team2.sandbox.web.role.UpdateRoleDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends GeneralServiceImpl<RoleEntity, ResponseRoleDto, CreateRoleDto, UpdateRoleDto>
        implements RoleService {

    private final PermissionService permissionService;

    public RoleServiceImpl(RoleDAO roleDAO, PermissionService permissionService, RoleMapper roleMapper) {
        this.generalDAO = roleDAO;
        this.permissionService = permissionService;
        this.generalMapper = roleMapper;
    }
}

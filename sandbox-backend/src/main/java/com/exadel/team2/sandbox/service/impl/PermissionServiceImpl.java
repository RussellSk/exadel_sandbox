package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.PermissionDAO;
import com.exadel.team2.sandbox.entity.PermissionEntity;
import com.exadel.team2.sandbox.mapper.PermissionMapper;
import com.exadel.team2.sandbox.service.PermissionService;
import com.exadel.team2.sandbox.web.GeneralDto;
import com.exadel.team2.sandbox.web.permission.ResponsePermissionDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PermissionServiceImpl extends GeneralServiceImpl<PermissionEntity, ResponsePermissionDto, GeneralDto, GeneralDto>
        implements PermissionService {

    public PermissionServiceImpl(PermissionDAO permissionDAO, PermissionMapper permissionMapper) {
        this.generalDAO = permissionDAO;
        this.generalMapper = permissionMapper;
    }
}

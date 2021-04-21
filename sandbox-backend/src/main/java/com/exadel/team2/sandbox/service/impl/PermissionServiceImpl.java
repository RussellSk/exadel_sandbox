package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.PermissionDAO;
import com.exadel.team2.sandbox.entity.PermissionEntity;
import com.exadel.team2.sandbox.mapper.PermissionMapper;
import com.exadel.team2.sandbox.service.GeneralService;
import com.exadel.team2.sandbox.service.PermissionService;
import com.exadel.team2.sandbox.web.GeneralDto;
import com.exadel.team2.sandbox.web.permission.ResponsePermissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PermissionServiceImpl extends GeneralServiceImpl<PermissionEntity, ResponsePermissionDto, GeneralDto, GeneralDto>
        implements PermissionService {

    public PermissionServiceImpl(PermissionDAO permissionDAO, PermissionMapper permissionMapper) {
        this.generalDAO = permissionDAO;
        this.generalMapper = permissionMapper;
    }
}

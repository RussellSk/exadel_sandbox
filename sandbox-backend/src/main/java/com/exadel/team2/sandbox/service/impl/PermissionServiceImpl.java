package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.PermissionDAO;
import com.exadel.team2.sandbox.entity.PermissionEntity;
import com.exadel.team2.sandbox.mapper.PermissionMapper;
import com.exadel.team2.sandbox.service.PermissionService;
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
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionDAO permissionDAO;
    private final PermissionMapper permissionMapper;

    @Override
    public ResponsePermissionDto getById(Long id) {
        PermissionEntity permissionEntity = permissionDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission Not Found"));

        return permissionMapper.convertEntityToDto(permissionEntity);
    }

    @Override
    public List<ResponsePermissionDto> getAll() {
        return permissionDAO.findAll().stream()
                .map(permissionMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

}

package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.RoleDAO;
import com.exadel.team2.sandbox.entity.PermissionEntity;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.mapper.RoleMapper;
import com.exadel.team2.sandbox.service.PermissionService;
import com.exadel.team2.sandbox.service.RoleService;
import com.exadel.team2.sandbox.web.role.ResponseRoleDto;
import com.exadel.team2.sandbox.web.role.UpdateRoleDto;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;
    private final PermissionService permissionService;
    private final RoleMapper roleMapper;

    @Override
    public ResponseRoleDto getById(Long id) {
        RoleEntity roleEntity = roleDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));
        return roleMapper.convertEntityToDto(roleEntity);
    }

    @Override
    public List<ResponseRoleDto> getAll() {
        List<RoleEntity> roleEntities = roleDAO.findAll();
        if (roleEntities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Content");
        }

        return roleEntities.stream()
                .map(roleMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ResponseRoleDto> getAllPageable(Pageable pageable) {
        return roleDAO.findAll(pageable)
                .map(roleMapper::convertEntityToDto);
    }

    @Override
    public ResponseRoleDto update(Long id, UpdateRoleDto updateRoleDto) {
        RoleEntity roleEntity = roleDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));

        roleEntity.setRlName(updateRoleDto.getRlName());
        roleEntity.setRlDescription(updateRoleDto.getRlDescription());

        roleEntity.setRlCreatedAt(LocalDateTime.now());
        roleEntity.setRlUpdatedAt(LocalDateTime.now());
        roleDAO.save(roleEntity);

        return roleMapper.convertEntityToDto(roleEntity);
    }

}

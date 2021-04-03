package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.RoleDAO;
import com.exadel.team2.sandbox.entity.PermissionEntity;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.service.PermissionService;
import com.exadel.team2.sandbox.service.RoleService;
import com.exadel.team2.sandbox.web.role.CreateRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;
    private final PermissionService permissionService;

    @Override
    public RoleEntity getById(Long id) {
        return roleDAO.findById(id).orElse(null);
    }

    @Override
    public List<RoleEntity> getAll() {
        return roleDAO.findAll();
    }

    @Override
    public Page<RoleEntity> getAllPageable(Pageable pageable) {
        return roleDAO.findAll(pageable);
    }

    @Override
    public RoleEntity update(Long id, CreateRoleDto createRoleDto) {
        RoleEntity roleEntity = getById(id);
        roleEntity.setRlName(createRoleDto.getRlName());
        roleEntity.setRlDescription(createRoleDto.getRlDescription());

        if (!createRoleDto.getPermissions().isEmpty()) {
            List<PermissionEntity> permissionEntities = new ArrayList<>();
            for (Long permissionId : createRoleDto.getPermissions()) {
                PermissionEntity permissionEntity = permissionService.getById(permissionId);
                permissionEntities.add(permissionEntity);
            }
            roleEntity.setPermissions(permissionEntities);
        }

        roleEntity.setRlCreatedAt(LocalDateTime.now());
        roleEntity.setRlUpdatedAt(LocalDateTime.now());

        return roleDAO.save(roleEntity);
    }

}

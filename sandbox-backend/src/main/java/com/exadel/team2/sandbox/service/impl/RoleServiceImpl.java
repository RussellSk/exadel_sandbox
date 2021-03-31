package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.RoleDAO;
import com.exadel.team2.sandbox.entity.PermissionEntity;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.service.PermissionService;
import com.exadel.team2.sandbox.service.RoleService;
import com.exadel.team2.sandbox.web.RoleDTO;
import lombok.RequiredArgsConstructor;
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
    public RoleEntity save(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRlName(roleDTO.getRlName());
        roleEntity.setRlDescription(roleDTO.getRlDescription());
        if (!roleDTO.getPermissions().isEmpty()) {
            List<PermissionEntity> permissionEntities = new ArrayList<>();
            for (Long permissionId : roleDTO.getPermissions()) {
                PermissionEntity permissionEntity = permissionService.getById(permissionId);
                permissionEntities.add(permissionEntity);
            }
            roleEntity.setPermissions(permissionEntities);
        }
        roleEntity.setRlCreatedAt(LocalDateTime.now());
        roleEntity.setRlUpdatedAt(LocalDateTime.now());
        return roleDAO.save(roleEntity);
    }

    @Override
    public RoleEntity update(Long id, RoleDTO roleDTO) {
        RoleEntity roleEntity = getById(id);
        roleEntity.setRlName(roleDTO.getRlName());
        roleEntity.setRlDescription(roleDTO.getRlDescription());

        if (!roleDTO.getPermissions().isEmpty()) {
            List<PermissionEntity> permissionEntities = new ArrayList<>();
            for (Long permissionId : roleDTO.getPermissions()) {
                PermissionEntity permissionEntity = permissionService.getById(permissionId);
                permissionEntities.add(permissionEntity);
            }
            roleEntity.setPermissions(permissionEntities);
        }

        roleEntity.setRlCreatedAt(LocalDateTime.now());
        roleEntity.setRlUpdatedAt(LocalDateTime.now());

        return roleDAO.save(roleEntity);
    }

    @Override
    public void delete(Long id) {
        roleDAO.deleteById(id);
    }
}

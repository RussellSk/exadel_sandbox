package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.PermissionEntity;

import java.util.List;

public interface PermissionService {
    PermissionEntity getById(Long id);
    List<PermissionEntity> getAll();
    PermissionEntity save(PermissionEntity permissionEntity);
    PermissionEntity update(PermissionEntity permissionEntity);
    void delete(Long id);
}

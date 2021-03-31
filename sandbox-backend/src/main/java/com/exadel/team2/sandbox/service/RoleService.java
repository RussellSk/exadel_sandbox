package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.RoleEntity;

import java.util.List;

public interface RoleService {
    RoleEntity getById(Long id);
    List<RoleEntity> getAll();
    RoleEntity save(RoleEntity roleEntity);
    RoleEntity update(RoleEntity roleEntity);
    void delete(Long id);
}

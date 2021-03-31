package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.web.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleEntity getById(Long id);
    List<RoleEntity> getAll();
    RoleEntity save(RoleDTO roleDTO);
    RoleEntity update(Long id, RoleDTO roleDTO);
    void delete(Long id);
}

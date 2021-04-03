package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.web.role.CreateRoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    RoleEntity getById(Long id);
    List<RoleEntity> getAll();
    Page<RoleEntity> getAllPageable(Pageable pageable);
    RoleEntity save(CreateRoleDto createRoleDto);
    RoleEntity update(Long id, CreateRoleDto createRoleDto);
    void delete(Long id);
}

package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.PermissionEntity;
import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.service.PermissionService;
import com.exadel.team2.sandbox.service.RoleService;
import com.exadel.team2.sandbox.web.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final PermissionService permissionService;

    @GetMapping("/{id}")
    public RoleEntity getRoleById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @GetMapping
    public List<RoleEntity> getRoles() {
        return roleService.getAll();
    }

    @PostMapping
    public RoleEntity createRole(@RequestBody RoleDTO roleDTO) {
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
        roleEntity = roleService.save(roleEntity);
        return roleEntity;
    }

    @PutMapping("/{id}")
    public RoleEntity updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        RoleEntity roleEntity = roleService.getById(id);
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
        roleEntity = roleService.update(roleEntity);

        return roleEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        RoleEntity roleEntity = roleService.getById(id);
        if (roleEntity == null) {
            return ResponseEntity.notFound().build();
        }

        roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}

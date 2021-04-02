package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.service.PermissionService;
import com.exadel.team2.sandbox.service.RoleService;
import com.exadel.team2.sandbox.web.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<RoleEntity> getRoles(
            @RequestParam(defaultValue = "0", name = "page") Integer page,
            @RequestParam(defaultValue = "15", name = "itemsPerPage") Integer itemsPerPage
    ) {
        return roleService.getAllPageable(PageRequest.of(page, itemsPerPage));
    }

    @PostMapping
    public RoleEntity createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }

    @PutMapping("/{id}")
    public RoleEntity updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        return roleService.update(id, roleDTO);
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

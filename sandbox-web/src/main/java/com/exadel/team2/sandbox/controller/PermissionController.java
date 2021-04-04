package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.PermissionEntity;
import com.exadel.team2.sandbox.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/{id}")
    public PermissionEntity getPermissionById(@PathVariable Long id) {
        return permissionService.getById(id);
    }

    @GetMapping
    public List<PermissionEntity> getPermissions() {
        return permissionService.getAll();
    }
}

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
    public ResponseEntity<PermissionEntity> getPermissionById(@PathVariable Long id) {
        PermissionEntity permissionEntity = permissionService.getById(id);
        if (permissionEntity == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(permissionEntity);
    }

    @GetMapping
    public ResponseEntity<List<PermissionEntity>> getPermissions() {
        return ResponseEntity.ok(permissionService.getAll());
    }
}

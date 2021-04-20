package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.PermissionService;
import com.exadel.team2.sandbox.web.permission.ResponsePermissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/{id}")
    public ResponsePermissionDto getPermissionById(@PathVariable Long id) {
        return permissionService.getById(id);
    }

    @GetMapping
    public List<ResponsePermissionDto> getPermissions() {
        return permissionService.getAll();
    }
}

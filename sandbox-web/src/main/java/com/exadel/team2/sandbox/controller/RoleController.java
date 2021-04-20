package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.PermissionService;
import com.exadel.team2.sandbox.service.RoleService;
import com.exadel.team2.sandbox.web.role.ResponseRoleDto;
import com.exadel.team2.sandbox.web.role.UpdateRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final PermissionService permissionService;

    @GetMapping("/{id}")
    public ResponseRoleDto getRoleById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @GetMapping
    public Page<ResponseRoleDto> getRoles(
            @RequestParam(defaultValue = "0", name = "page") Integer page,
            @RequestParam(defaultValue = "15", name = "itemsPerPage") Integer itemsPerPage) {
        return roleService.getAllPageable(PageRequest.of(page, itemsPerPage), "");
    }

    @PutMapping("/{id}")
    public ResponseRoleDto updateRole(@PathVariable Long id, @Validated @RequestBody UpdateRoleDto updateRoleDto) {
        return roleService.update(id, updateRoleDto);
    }
}

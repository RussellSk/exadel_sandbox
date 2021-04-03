package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.role.ResponseRoleDto;
import com.exadel.team2.sandbox.web.role.UpdateRoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    ResponseRoleDto getById(Long id);
    List<ResponseRoleDto> getAll();
    Page<ResponseRoleDto> getAllPageable(Pageable pageable);
    ResponseRoleDto update(Long id, UpdateRoleDto updateRoleDto);
}

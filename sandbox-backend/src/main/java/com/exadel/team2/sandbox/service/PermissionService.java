package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.permission.ResponsePermissionDto;

import java.util.List;

public interface PermissionService {
    ResponsePermissionDto getById(Long id);
    List<ResponsePermissionDto> getAll();
}

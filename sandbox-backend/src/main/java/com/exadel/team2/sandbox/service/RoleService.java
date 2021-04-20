package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.role.CreateRoleDto;
import com.exadel.team2.sandbox.web.role.ResponseRoleDto;
import com.exadel.team2.sandbox.web.role.UpdateRoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService extends GeneralService<ResponseRoleDto, CreateRoleDto, UpdateRoleDto> {
}

package com.exadel.team2.sandbox.web.role;

import com.exadel.team2.sandbox.entity.PermissionEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateRoleDto {
    private String rlName;
    private String rlDescription;
    private List<Long> permissions = new ArrayList<>();
}

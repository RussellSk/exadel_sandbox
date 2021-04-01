package com.exadel.team2.sandbox.web;

import com.exadel.team2.sandbox.entity.PermissionEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleDTO {
    private String rlName;
    private String rlDescription;
    private List<Long> permissions = new ArrayList<>();
}

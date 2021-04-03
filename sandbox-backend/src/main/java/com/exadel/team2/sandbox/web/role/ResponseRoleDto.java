package com.exadel.team2.sandbox.web.role;

import com.exadel.team2.sandbox.web.permission.ResponsePermissionDto;
import lombok.Data;
import java.util.List;

@Data
public class ResponseRoleDto {
    private Long rlId;
    private String name;
    private String description;
    private List<ResponsePermissionDto> permissions;
}

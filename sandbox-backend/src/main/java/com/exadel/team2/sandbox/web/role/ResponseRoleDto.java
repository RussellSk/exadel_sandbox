package com.exadel.team2.sandbox.web.role;

import com.exadel.team2.sandbox.web.GeneralDto;
import com.exadel.team2.sandbox.web.permission.ResponsePermissionDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ResponseRoleDto implements GeneralDto {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private List<ResponsePermissionDto> permissions;
}

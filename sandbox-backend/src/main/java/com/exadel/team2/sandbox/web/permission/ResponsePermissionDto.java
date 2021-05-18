package com.exadel.team2.sandbox.web.permission;

import com.exadel.team2.sandbox.web.GeneralDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResponsePermissionDto implements GeneralDto {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String route;

    @NotNull
    private String httpMethod;
}

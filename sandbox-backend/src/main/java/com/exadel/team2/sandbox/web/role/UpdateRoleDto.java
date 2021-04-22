package com.exadel.team2.sandbox.web.role;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UpdateRoleDto {

    @Size(min = 1, max = 255, message = "rlName must be between 1 and 255 characters")
    private String rlName;

    @Size(min = 1, max = 255, message = "rlDescription must be between 1 and 255 characters")
    private String rlDescription;
}
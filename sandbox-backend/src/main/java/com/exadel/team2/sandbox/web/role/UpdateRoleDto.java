package com.exadel.team2.sandbox.web.role;

import com.exadel.team2.sandbox.web.GeneralDto;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UpdateRoleDto implements GeneralDto {

    @Size(min = 1, max = 255, message = "rlName must be between 1 and 255 characters")
    private String name;

    @Size(min = 1, max = 255, message = "rlDescription must be between 1 and 255 characters")
    private String description;
}

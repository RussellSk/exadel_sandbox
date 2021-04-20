package com.exadel.team2.sandbox.web.permission;

import com.exadel.team2.sandbox.web.GeneralDto;
import lombok.Data;

@Data
public class ResponsePermissionDto implements GeneralDto {
    private Long id;
    private String name;
}

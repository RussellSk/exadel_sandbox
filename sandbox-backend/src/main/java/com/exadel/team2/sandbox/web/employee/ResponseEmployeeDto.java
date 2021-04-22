package com.exadel.team2.sandbox.web.employee;

import com.exadel.team2.sandbox.web.GeneralDto;
import com.exadel.team2.sandbox.web.role.ResponseRoleDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ResponseEmployeeDto implements GeneralDto {
    @NotNull
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String phone;

    @NotNull
    private String email;

    private String skype;

    private String locationCountry;

    private String locationCity;

    private String timezone;

    @NotNull
    private ResponseRoleDto role;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;
}
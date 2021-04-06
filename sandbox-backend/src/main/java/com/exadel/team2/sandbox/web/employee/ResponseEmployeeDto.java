package com.exadel.team2.sandbox.web.employee;

import com.exadel.team2.sandbox.web.role.ResponseRoleDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ResponseEmployeeDto {
    @NotNull
    private Long empId;

    @NotNull
    private String empFirstName;

    @NotNull
    private String empLastName;

    private String empPhone;

    @NotNull
    private String empEmail;

    private String empSkype;

    @NotNull
    private ResponseRoleDto role;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;
}

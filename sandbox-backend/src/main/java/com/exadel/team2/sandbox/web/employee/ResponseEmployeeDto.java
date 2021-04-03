package com.exadel.team2.sandbox.web.employee;

import com.exadel.team2.sandbox.web.role.ResponseRoleDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseEmployeeDto {
    private Long empId;
    private String empFirstName;
    private String empLastName;
    private ResponseRoleDto role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

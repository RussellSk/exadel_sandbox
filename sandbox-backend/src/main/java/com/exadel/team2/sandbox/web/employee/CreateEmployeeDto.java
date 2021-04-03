package com.exadel.team2.sandbox.web.employee;

import lombok.Data;

@Data
public class CreateEmployeeDto {
    private String empFirstName;
    private String empLastName;
    private Long roleId;
}

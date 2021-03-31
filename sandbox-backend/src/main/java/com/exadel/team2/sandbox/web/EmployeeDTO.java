package com.exadel.team2.sandbox.web;

import lombok.Data;

@Data
public class EmployeeDTO {
    public EmployeeDTO() {}

    public EmployeeDTO(String empFirstName, String empLastName, Long roleId) {
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.roleId = roleId;
    }

    private String empFirstName;
    private String empLastName;
    private Long roleId;
}

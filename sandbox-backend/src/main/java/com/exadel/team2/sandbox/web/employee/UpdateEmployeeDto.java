package com.exadel.team2.sandbox.web.employee;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UpdateEmployeeDto {
    @Size(min = 1, max = 255, message = "empFirstName must be between 1 and 255 characters")
    private String empFirstName;

    @Size(min = 1, max = 255, message = "empLastName must be between 1 and 255 characters")
    private String empLastName;

    @Size(min = 1, max = 255, message = "empPhone must be between 1 and 255 characters")
    private String empPhone;

    @Email(message = "empEmail should be valid")
    @Size(min = 1, max = 255, message = "empEmail must be between 1 and 255 characters")
    private String empEmail;

    @Size(min = 1, max = 255, message = "empSkype must be between 1 and 255 characters")
    private String empSkype;

    private Long roleId;
}

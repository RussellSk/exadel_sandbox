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

    @Size(min = 1, max = 255, message = "empLocationCountry must be between 1 and 255 characters")
    private String empLocationCountry;

    @Size(min = 1, max = 255, message = "empLocationCity must be between 1 and 255 characters")
    private String empLocationCity;

    @Size(min = 1, max = 255, message = "empTimezone must be between 1 and 255 characters")
    private String empTimezone;

    @Size(min = 6,max = 25,message = "empPassword must be between 1 and 25 characters")
    private String empPassword;
}

package com.exadel.team2.sandbox.web.employee;

import com.exadel.team2.sandbox.web.GeneralDto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateEmployeeDto implements GeneralDto {

    @NotNull(message = "empFirstName cannot be null")
    @Size(min = 1, max = 255, message = "empFirstName must be between 1 and 255 characters")
    private String firstName;

    @NotNull(message = "empLastName cannot be null")
    @Size(min = 1, max = 255, message = "empLastName must be between 1 and 255 characters")
    private String lastName;

    @Size(min = 1, max = 255, message = "primaryTechnology must be between 1 and 255 characters")
    private String primaryTechnology;

    @Size(min = 1, max = 100, message = "type must be between 1 and 255 characters")
    private String type;

    @Size(min = 1, max = 255, message = "empPhone must be between 1 and 255 characters")
    private String phone;

    @NotNull(message = "empEmail cannot be null")
    @Email(message = "empEmail should be valid")
    @Size(min = 1, max = 255, message = "empEmail must be between 1 and 255 characters")
    private String email;

    @Size(min = 1, max = 255, message = "empSkype must be between 1 and 255 characters")
    private String skype;

    @NotNull(message = "roleId cannot be null")
    private Long roleId;

    @Size(min = 1, max = 255, message = "empLocationCountry must be between 1 and 255 characters")
    private String locationCountry;

    @Size(min = 1, max = 255, message = "empLocationCity must be between 1 and 255 characters")
    private String locationCity;

    @Size(min = 1, max = 255, message = "empTimezone must be between 1 and 255 characters")
    private String timezone;
}

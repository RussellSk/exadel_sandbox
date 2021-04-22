package com.exadel.team2.sandbox.web.employee;

import com.exadel.team2.sandbox.web.GeneralDto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UpdateEmployeeDto implements GeneralDto {
    @Size(min = 1, max = 255, message = "empFirstName must be between 1 and 255 characters")
    private String firstName;

    @Size(min = 1, max = 255, message = "empLastName must be between 1 and 255 characters")
    private String lastName;

    @Size(min = 1, max = 255, message = "empPhone must be between 1 and 255 characters")
    private String phone;

    @Email(message = "empEmail should be valid")
    @Size(min = 1, max = 255, message = "empEmail must be between 1 and 255 characters")
    private String email;

    @Size(min = 1, max = 255, message = "empSkype must be between 1 and 255 characters")
    private String skype;

    private Long roleId;

    @Size(min = 1, max = 255, message = "empLocationCountry must be between 1 and 255 characters")
    private String locationCountry;

    @Size(min = 1, max = 255, message = "empLocationCity must be between 1 and 255 characters")
    private String locationCity;

    @Size(min = 1, max = 255, message = "empTimezone must be between 1 and 255 characters")
    private String timezone;

    @Size(min = 6,max = 25,message = "empPassword must be between 1 and 25 characters")
    private String password;
}

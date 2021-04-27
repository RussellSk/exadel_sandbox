package com.exadel.team2.sandbox.web.employee;

import com.exadel.team2.sandbox.web.GeneralDto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateEmployeeDto implements GeneralDto {

    @NotNull(message = "firstName cannot be null")
    @Size(min = 1, max = 255, message = "firstName must be between 1 and 255 characters")
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    @Size(min = 1, max = 255, message = "lastName must be between 1 and 255 characters")
    private String lastName;

    @Size(min = 1, max = 255, message = "phone must be between 1 and 255 characters")
    private String phone;

    @NotNull(message = "email cannot be null")
    @Email(message = "email should be valid")
    @Size(min = 1, max = 255, message = "email must be between 1 and 255 characters")
    private String email;

    @Size(min = 1, max = 255, message = "skype must be between 1 and 255 characters")
    private String skype;

    @NotNull(message = "password cannot be null")
    @Size(min = 6, max = 255, message = "password must be between 6 and 255 characters")
    private String password;

    @NotNull(message = "roleId cannot be null")
    private Long roleId;

    @Size(min = 1, max = 255, message = "locationCountry must be between 1 and 255 characters")
    private String locationCountry;

    @Size(min = 1, max = 255, message = "locationCity must be between 1 and 255 characters")
    private String locationCity;

    @Size(min = 1, max = 255, message = "timezone must be between 1 and 255 characters")
    private String timezone;
}

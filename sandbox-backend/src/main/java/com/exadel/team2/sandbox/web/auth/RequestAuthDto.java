package com.exadel.team2.sandbox.web.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestAuthDto {

    @NotNull
    @Email(message = "email should be valid")
    @Size(min = 3, max = 255, message = "email must be between 3 and 255 characters")
    private String email;

    @NotNull
    @Size(min = 6, max = 255, message = "password must be between 6 and 255 characters")
    private String password;
}

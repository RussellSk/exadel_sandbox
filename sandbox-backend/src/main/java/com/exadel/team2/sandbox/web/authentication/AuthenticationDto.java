package com.exadel.team2.sandbox.web.authentication;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthenticationDto {

    @NotNull
    private String empEmail;

    @NotNull
    private String empPassword;
}

package com.exadel.team2.sandbox.web.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAuthenticationDto {

    @NotNull
    private String email;

    @NotNull
    private String token;
}

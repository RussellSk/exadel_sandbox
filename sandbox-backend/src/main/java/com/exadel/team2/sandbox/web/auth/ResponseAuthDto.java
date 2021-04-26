package com.exadel.team2.sandbox.web.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResponseAuthDto {

    @NotNull
    private String token;
}

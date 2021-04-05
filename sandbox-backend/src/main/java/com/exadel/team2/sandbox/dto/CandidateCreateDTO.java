package com.exadel.team2.sandbox.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CandidateCreateDTO {

    @NotNull
    private Long rsmId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @NotNull
    private String skype;

    @NotNull
    private String englishLevel;

    @NotNull
    private String expertise;

    @NotNull
    private String experience;

    @NotNull
    private String education;

    @NotNull
    private String location;
}

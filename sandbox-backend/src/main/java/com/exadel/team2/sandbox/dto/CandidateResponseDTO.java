package com.exadel.team2.sandbox.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor

public class CandidateResponseDTO {

    @NotNull
    private Long id;

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

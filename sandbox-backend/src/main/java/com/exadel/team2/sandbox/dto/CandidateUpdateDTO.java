package com.exadel.team2.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor

public class CandidateUpdateDTO {

    @Id
    @NotNull
    private Long id;

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

    private String expertise;

    @NotNull
    private String experience;

    private String education;

    @NotNull
    private String location;

    @JsonIgnore
    private final LocalDateTime updatedAt = LocalDateTime.now();
}

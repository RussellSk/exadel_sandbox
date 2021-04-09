package com.exadel.team2.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Builder
public class CandidateUpdateDTO {

    private Long id;

    private Long rsmId;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String skype;

    private String englishLevel;

    private String expertise;

    private String experience;

    private String education;

    private String location;

    @JsonIgnore
    private final LocalDateTime updatedAt = LocalDateTime.now();
}

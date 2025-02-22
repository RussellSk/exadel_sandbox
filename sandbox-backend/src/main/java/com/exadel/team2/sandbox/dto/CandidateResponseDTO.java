package com.exadel.team2.sandbox.dto;

import lombok.Data;
import com.exadel.team2.sandbox.entity.enums.CandidateStatus;
import com.exadel.team2.sandbox.web.canidate_availability_time.CreateCandidateAvailabilityTimeDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CandidateResponseDTO {

    @NotNull
    private Long id;

    @NotNull
    private List<CreateCandidateAvailabilityTimeDto> availabilityTimeSlots;

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
    private String mainSkill;

    @NotNull
    private String otherSkills;

    @NotNull
    private String institution;

    @NotNull
    private String faculty;

    @NotNull
    private String speciality;

    @NotNull
    private LocalDate graduationDate;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private LocalDateTime interviewTime;

    @NotNull
    private CandidateStatus status;
}

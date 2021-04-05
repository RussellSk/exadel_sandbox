package com.exadel.team2.sandbox.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
//@Builder
@NoArgsConstructor

public class CandidateResponseDTO {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private Long rsm_id;

    @NotNull
    private String cn_first_name;

    @NotNull
    private String cn_last_name;

    @NotNull
    private String cn_phone;

    @NotNull
    private String cn_email;

    @NotNull
    private String cn_skype;

    @NotNull
    private String cn_english_level;

    @NotNull
    private String cn_expertise;

    @NotNull
    private String cn_experience;

    @NotNull
    private String cn_education;

    @NotNull
    private String cn_location;

}

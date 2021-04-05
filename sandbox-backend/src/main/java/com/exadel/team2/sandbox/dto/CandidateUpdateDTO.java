package com.exadel.team2.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder

public class CandidateUpdateDTO {

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

    private String cn_expertise;

    private String cn_experience;

    private String cn_education;

    private String cn_location;

    @JsonIgnore
    private final LocalDateTime cn_updated_at = LocalDateTime.now();
}

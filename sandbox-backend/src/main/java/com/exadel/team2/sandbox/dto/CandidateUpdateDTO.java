package com.exadel.team2.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter

public class CandidateUpdateDAO {

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

    private String cn_skype;

    @NotNull
    private String cn_english_level;

    private String cn_expertise;

    private String cn_experience;

    private String cn_education;

    private String cn_location;

    @JsonIgnore
    private LocalDateTime cn_updated_at = LocalDateTime.now();
}

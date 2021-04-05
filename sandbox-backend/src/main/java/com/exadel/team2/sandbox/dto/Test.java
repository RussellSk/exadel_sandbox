package com.exadel.team2.sandbox.dto;

import com.exadel.team2.sandbox.entity.CandidateEntity;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        CandidateUpdateDTO candidateUpdateDTO = CandidateUpdateDTO.builder()
                .cn_email("///")
                .cn_english_level("...")
                .cn_first_name("...")
                .cn_phone("...")
                .cn_skype("...")
                .cn_location("...")
                .id(1L)
                .rsm_id(1L)
                .build();

        CandidateEntity candidateEntity = new ModelMapper()
                .map(candidateUpdateDTO, CandidateEntity.class);

        System.out.println(candidateEntity);
    }
}

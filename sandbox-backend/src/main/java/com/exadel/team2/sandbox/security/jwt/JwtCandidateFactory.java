package com.exadel.team2.sandbox.security.jwt;

import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.entity.CandidateEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class JwtCandidateFactory {

    public static JwtCandidate create(CandidateResponseDTO candidateResponseDTO) {
        return new JwtCandidate(
                candidateResponseDTO.getId(),
                candidateResponseDTO.getRsmId(),
                candidateResponseDTO.getFirstName(),
                candidateResponseDTO.getLastName(),
                candidateResponseDTO.getPhone(),
                candidateResponseDTO.getEmail(),
                candidateResponseDTO.getSkype(),
                candidateResponseDTO.getEnglishLevel(),
                candidateResponseDTO.getMainSkill(),
                candidateResponseDTO.getOtherSkills(),
                candidateResponseDTO.getInstitution(),
                candidateResponseDTO.getFaculty(),
                candidateResponseDTO.getSpeciality(),
                candidateResponseDTO.getGraduationDate(),
                candidateResponseDTO.getCountry(),
                candidateResponseDTO.getCity()
        );
    }
}

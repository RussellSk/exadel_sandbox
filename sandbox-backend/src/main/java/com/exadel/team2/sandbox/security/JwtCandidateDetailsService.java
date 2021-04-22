package com.exadel.team2.sandbox.security;

import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.security.jwt.JwtCandidate;
import com.exadel.team2.sandbox.security.jwt.JwtCandidateDetails;
import com.exadel.team2.sandbox.security.jwt.JwtCandidateFactory;
import com.exadel.team2.sandbox.service.CandidateService;
import com.exadel.team2.sandbox.service.impl.CandidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtCandidateDetailsService implements JwtCandidateDetails {

    private final CandidateService candidateService;

    @Autowired
    public JwtCandidateDetailsService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }


    @Override
    public JwtCandidate loadCandidateById(Long id) {
        CandidateResponseDTO candidateResponseDTO = candidateService.findById(id);

        JwtCandidate jwtCandidate = JwtCandidateFactory.create(candidateResponseDTO);
        return jwtCandidate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

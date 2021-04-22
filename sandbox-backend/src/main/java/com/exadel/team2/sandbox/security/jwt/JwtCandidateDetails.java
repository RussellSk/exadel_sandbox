package com.exadel.team2.sandbox.security.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public interface JwtCandidateDetails extends UserDetailsService {
    JwtCandidate loadCandidateById(Long id);
}

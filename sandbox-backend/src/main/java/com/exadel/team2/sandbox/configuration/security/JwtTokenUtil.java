package com.exadel.team2.sandbox.configuration.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final SecurityParams securityParams;

    public String generateAccessToken(EmployeeEntity employeeEntity) {
        return JWT.create()
                .withSubject(employeeEntity.getEmail())
                .withIssuer(securityParams.getTokenIssuer())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + securityParams.getExpirationTime()))
                .sign(Algorithm.HMAC512(securityParams.getSecret().getBytes()));
    }

    public String getEmail(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(securityParams.getSecret()))
                    .withIssuer(securityParams.getTokenIssuer())
                    .build()
                    .verify(token.replace(securityParams.getTokenPrefix(), ""));
            return decodedJWT.getSubject();

        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}

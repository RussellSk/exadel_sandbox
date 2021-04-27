package com.exadel.team2.sandbox.configuration.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    public String generateAccessToken(EmployeeEntity employeeEntity) {
        return JWT.create()
                .withSubject(employeeEntity.getEmail())
                .withIssuer(SecurityConstants.TOKEN_ISSUER)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
    }

    public String getEmail(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET))
                    .withIssuer(SecurityConstants.TOKEN_ISSUER)
                    .build()
                    .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
            return decodedJWT.getSubject();

        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}

package com.exadel.team2.sandbox.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityParams {
    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration-date}")
    private String EXPIRATION_TIME;

    @Value("${jwt.token-prefix}")
    private String TOKEN_PREFIX;

    @Value("${jwt.token-issuer}")
    private String TOKEN_ISSUER;

    @Value("${jwt.header-string}")
    private String HEADER_STRING;

    public String getSecret() {
        return SECRET;
    }

    public Long getExpirationTime() {
        return Long.valueOf(EXPIRATION_TIME);
    }

    public String getTokenPrefix() {
        return TOKEN_PREFIX + " ";
    }

    public String getTokenIssuer() {
        return TOKEN_ISSUER;
    }

    public String getHeaderString() {
        return HEADER_STRING;
    }
}

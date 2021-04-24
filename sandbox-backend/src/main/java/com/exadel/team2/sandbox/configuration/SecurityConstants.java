package com.exadel.team2.sandbox.configuration;

public final class SecurityConstants {
    public static final String SECRET = "iazMqvVFVf9JaI8SHUA8O7uM1ZnEztXktOHUujxzrmTCT46Q6iCIk1l8A4NZ9Qw";
    public static final Long EXPIRATION_TIME = 900_000L; // 15 min
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/admin/login";
}

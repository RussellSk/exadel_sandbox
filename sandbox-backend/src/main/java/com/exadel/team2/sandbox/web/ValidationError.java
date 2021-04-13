package com.exadel.team2.sandbox.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ValidationError {
    private final String fieldName;
    private final String message;
}

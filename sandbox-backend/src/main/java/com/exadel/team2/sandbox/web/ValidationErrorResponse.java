package com.exadel.team2.sandbox.web;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {
    private List<ValidationError> violations = new ArrayList<>();
}

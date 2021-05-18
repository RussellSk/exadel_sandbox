package com.exadel.team2.sandbox.web;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class MessageDTO {
    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private String subject;
    @NotNull
    private String text;

    private Map<String, Object> properties;

    private String templateName;
}

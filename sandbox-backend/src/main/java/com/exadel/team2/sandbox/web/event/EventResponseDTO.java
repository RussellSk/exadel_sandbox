package com.exadel.team2.sandbox.web.event;

import com.exadel.team2.sandbox.entity.enums.EnglishLevel;
import com.exadel.team2.sandbox.entity.enums.EventTab;
import com.exadel.team2.sandbox.entity.enums.Format;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import com.exadel.team2.sandbox.web.event_type.EventTypeResponseDTO;
import com.exadel.team2.sandbox.web.image.ImageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {

    private Long id;
    private ImageResponseDTO image;
    private ResponseEmployeeDto employee;
    private EventTypeResponseDTO eventType;
    private ResponseEmployeeDto creatorEvent;
    private LocalDate startDate;
    private String duration;
    private LocalDate deadline;
    @NotNull
    private LocalDate dateOfEndAccept;
    @NotNull
    private Format format;
    private String country;
    private String city;
    private String technologies;
    private EventTab eventTab;
    private EnglishLevel englishLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
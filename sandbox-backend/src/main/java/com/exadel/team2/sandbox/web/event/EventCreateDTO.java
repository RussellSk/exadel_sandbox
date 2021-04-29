package com.exadel.team2.sandbox.web.event;

import com.exadel.team2.sandbox.entity.enums.EnglishLevel;
import com.exadel.team2.sandbox.entity.enums.EventTab;
import com.exadel.team2.sandbox.entity.enums.Format;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateDTO {

    private Long image;
    private Long employee;
    private Long eventType;

    @NotNull
    private Long creatorEvent;
    private LocalDate startDate;

    @Size(min = 1, max = 50, message = "Duration must be between 1 and 50 characters")
    private String duration;
    private LocalDate deadline;

    @NotNull
    private LocalDate dateOfEndAccept;

    @NotNull
    private Format format;
    private String country;
    private String city;
    private String technologies;
    private String title;
    private String description;
    private EventTab eventTab;
    private EnglishLevel englishLevel;
}

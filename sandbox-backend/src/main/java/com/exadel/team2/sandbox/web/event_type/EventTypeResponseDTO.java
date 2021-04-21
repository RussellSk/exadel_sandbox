package com.exadel.team2.sandbox.web.event_type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeResponseDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;

}

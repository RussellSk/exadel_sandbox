package com.exadel.team2.sandbox.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeResponseDTO {

    private Long evtId;
    @NotNull
    private String evtName;
    @NotNull
    private String evtDescription;
    private LocalDateTime evtCreatedAt;
    private LocalDateTime evtUpdatedAt;

}

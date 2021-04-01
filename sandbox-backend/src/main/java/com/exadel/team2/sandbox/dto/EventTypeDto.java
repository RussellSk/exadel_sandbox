package com.exadel.team2.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeDto {

    private Long evtId;
    private Long eventId;
    private String evtName;
    private String evtDescription;

}

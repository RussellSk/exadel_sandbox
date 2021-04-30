package com.exadel.team2.sandbox.web.statushistory;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateStatusHistoryDTO {

    @NotNull(message = "Status id cannot be null")
    private Long status;

    @NotNull(message = "Candidate id cannot be null")
    private Long candidate;

    @NotNull(message = "Employee id cannot be null")
    private Long employee;

    private String changeNote;

}

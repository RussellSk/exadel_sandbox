package com.exadel.team2.sandbox.web.status;

import com.exadel.team2.sandbox.web.GeneralDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
public class ResponseStatusDTO implements GeneralDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

}

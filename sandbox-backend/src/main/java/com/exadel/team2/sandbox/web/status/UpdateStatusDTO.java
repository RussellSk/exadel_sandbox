package com.exadel.team2.sandbox.web.status;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class UpdateStatusDTO {

    @Size(min = 1, max = 55, message = "Status name must be between 1 and 55 characters")
    @NotNull(message = "Status name cannot be null")
    private String name;

    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    @NotNull(message = "Status description cannot be null")
    private String description;
}

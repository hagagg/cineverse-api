package com.hagag.cineverse.dto.movie;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieRequestDto {

    @NotNull (message = "TMDb Id is required")
    private Long tmdbId;

}

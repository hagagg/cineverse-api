package com.hagag.cineverse.dto.movie;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {

    @NotNull (message = "TMDb Id is required")
    private Long tmdbId;

}

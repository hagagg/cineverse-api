package com.hagag.cineverse.dto.movie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {

    @NotNull (message = "TMDb Id is required")
    private Long tmdbId;

    @NotBlank (message = "Title is required")
    @Size (max = 255 , message = "Title must be at most 255 characters")
    private String title;

    @Size(max = 500 , message = "Poster must be at most 500 characters")
    private String posterUrl;

    @NotNull (message = "Release date is required")
    private LocalDate releaseDate;

    @NotBlank (message = "Overview is required")
    private String overview;
}

package com.hagag.cineverse.dto.movie;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieResponseDto {

    private Long id;
    private Long tmdbId;
    private String title;
    private String posterUrl;
    private LocalDate releaseDate;
    private String overview;

}

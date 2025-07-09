package com.hagag.cineverse.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDto {

    private Long id;
    private Long tmdbId;
    private String title;
    private String posterUrl;
    private LocalDate releaseDate;
    private String overview;

}

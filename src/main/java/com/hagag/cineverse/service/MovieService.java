package com.hagag.cineverse.service;

import com.hagag.cineverse.dto.movie.MovieRequestDto;
import com.hagag.cineverse.dto.movie.MovieResponseDto;

public interface MovieService {

    MovieResponseDto createMovie(MovieRequestDto movieRequestDto);
}

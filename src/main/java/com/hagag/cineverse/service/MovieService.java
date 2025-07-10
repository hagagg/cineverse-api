package com.hagag.cineverse.service;

import com.hagag.cineverse.dto.movie.MovieResponseDto;
import com.hagag.cineverse.dto.movie.MovieUpdateDto;

import java.util.List;

public interface MovieService {

    MovieResponseDto createMovie(Long tmdbId);

    List<MovieResponseDto> getAllMovies();

    MovieResponseDto getMovieById(Long id);

    List<MovieResponseDto> searchByTitle(String title);

    MovieResponseDto updateMovie(Long id, MovieUpdateDto movieUpdateDto);

    void deleteMovie(Long id);

}

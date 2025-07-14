package com.hagag.cineverse.service;

import com.hagag.cineverse.dto.movie.MovieResponseDto;
import com.hagag.cineverse.dto.movie.MovieUpdateDto;
import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import org.springframework.data.domain.Pageable;


public interface MovieService {

    MovieResponseDto createMovie(Long tmdbId);

    PaginatedResponseDto<MovieResponseDto> getAllMovies(Pageable pageable);

    MovieResponseDto getMovieById(Long id);

    PaginatedResponseDto<MovieResponseDto> searchByTitle(String title , Pageable pageable);

    MovieResponseDto updateMovie(Long id, MovieUpdateDto movieUpdateDto);

    void deleteMovie(Long id);

}

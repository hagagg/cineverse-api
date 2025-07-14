package com.hagag.cineverse.service.impl;

import com.hagag.cineverse.client.TmdbClient;
import com.hagag.cineverse.dto.movie.MovieResponseDto;
import com.hagag.cineverse.dto.movie.MovieUpdateDto;
import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.dto.tmdb.TmdbMovieDto;
import com.hagag.cineverse.entity.Movie;
import com.hagag.cineverse.exception.custom.ResourceNotFoundException;
import com.hagag.cineverse.mapper.MovieMapper;
import com.hagag.cineverse.mapper.PaginationMapper;
import com.hagag.cineverse.repository.MovieRepo;
import com.hagag.cineverse.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;
    private final MovieMapper movieMapper;
    private final PaginationMapper paginationMapper;
    private final TmdbClient tmdbClient;

    @Override
    public MovieResponseDto createMovie(Long tmdbId) {
        if (movieRepo.existsByTmdbId(tmdbId)) {
            throw new IllegalArgumentException("Movie already exists");
        }

        TmdbMovieDto tmdbMovieDto = tmdbClient.fetchMovieById(tmdbId);

        Movie movie = movieMapper.toEntity(tmdbMovieDto);
        movie.setTmdbId(tmdbId);
        movieRepo.save(movie);

        return movieMapper.toDto(movie);
    }

    @Override
    public PaginatedResponseDto<MovieResponseDto> getAllMovies(Pageable pageable) {
        Page<Movie> moviesPage = movieRepo.findAll(pageable);

        Page<MovieResponseDto> dtoPage = moviesPage.map(movieMapper::toDto);

        return paginationMapper.toPaginatedResponse(dtoPage);
    }

    @Override
    public MovieResponseDto getMovieById(Long id) {
        Movie movie = movieRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found"));
        return movieMapper.toDto(movie);
    }

    @Override
    public PaginatedResponseDto<MovieResponseDto> searchByTitle(String title , Pageable pageable) {
        Page<Movie> moviesPage = movieRepo.findByTitleIgnoringSpacesAndCase(title , pageable);

        Page<MovieResponseDto> dtoPage = moviesPage.map(movieMapper::toDto);

        return paginationMapper.toPaginatedResponse(dtoPage);
    }

    @Override
    public MovieResponseDto updateMovie(Long id, MovieUpdateDto movieUpdateDto) {
        Movie movie = movieRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found"));

        if (movieUpdateDto.getTitle() != null) {movie.setTitle(movieUpdateDto.getTitle());}
        if (movieUpdateDto.getPosterUrl() != null) {movie.setPosterUrl(movieUpdateDto.getPosterUrl());}
        if (movieUpdateDto.getReleaseDate() != null) {movie.setReleaseDate(movieUpdateDto.getReleaseDate());}
        if (movieUpdateDto.getOverview() != null) {movie.setOverview(movieUpdateDto.getOverview());}

        movieRepo.save(movie);
        return movieMapper.toDto(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        Movie movie = movieRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found"));

        movieRepo.delete(movie);
    }
}

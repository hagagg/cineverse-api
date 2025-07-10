package com.hagag.cineverse.service.impl;

import com.hagag.cineverse.client.TmdbClient;
import com.hagag.cineverse.dto.movie.MovieResponseDto;
import com.hagag.cineverse.dto.movie.MovieUpdateDto;
import com.hagag.cineverse.dto.tmdb.TmdbMovieDto;
import com.hagag.cineverse.entity.Movie;
import com.hagag.cineverse.exception.custom.ResourceNotFoundException;
import com.hagag.cineverse.mapper.MovieMapper;
import com.hagag.cineverse.repository.MovieRepo;
import com.hagag.cineverse.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;
    private final MovieMapper movieMapper;
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
    public List<MovieResponseDto> getAllMovies() {
        List <Movie> allMovies = movieRepo.findAll();
        return allMovies.stream().map(movieMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public MovieResponseDto getMovieById(Long id) {
        Movie movie = movieRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found"));
        return movieMapper.toDto(movie);
    }

    @Override
    public List<MovieResponseDto> searchByTitle(String title) {
        List<Movie> movies = movieRepo.findByTitleIgnoringSpacesAndCase(title);
        return movies.stream().map(movieMapper::toDto).collect(Collectors.toList());
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

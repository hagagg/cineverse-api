package com.hagag.cineverse.service.impl;

import com.hagag.cineverse.client.TmdbClient;
import com.hagag.cineverse.dto.movie.MovieRequestDto;
import com.hagag.cineverse.dto.movie.MovieResponseDto;
import com.hagag.cineverse.dto.tmdb.TmdbMovieDto;
import com.hagag.cineverse.entity.Movie;
import com.hagag.cineverse.mapper.MovieMapper;
import com.hagag.cineverse.repository.MovieRepo;
import com.hagag.cineverse.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;
    private final MovieMapper movieMapper;
    private final TmdbClient tmdbClient;

    @Override
    public MovieResponseDto createMovie(MovieRequestDto movieRequestDto) {
        Long tmdbMovieId = movieRequestDto.getTmdbId();


        if (movieRepo.existsByTmdbId(tmdbMovieId)) {
            throw new IllegalArgumentException("Movie already exists");
        }

        TmdbMovieDto tmdbMovieDto = tmdbClient.fetchMovieById(tmdbMovieId);
        System.out.println("TMDb DTO fetched: " + tmdbMovieDto);


        Movie movie = movieMapper.toEntity(tmdbMovieDto , tmdbMovieId);
        movieRepo.save(movie);
        return movieMapper.toDto(movie);
    }
}

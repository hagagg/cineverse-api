package com.hagag.cineverse.controller;

import com.hagag.cineverse.dto.movie.MovieResponseDto;
import com.hagag.cineverse.dto.movie.MovieUpdateDto;
import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/tmdb/create/{tmdbId}")
    public MovieResponseDto createMovie(@PathVariable Long tmdbId) {
        return movieService.createMovie (tmdbId);
    }

    @GetMapping("/allMovies")
    public PaginatedResponseDto<MovieResponseDto> getAllMovies(Pageable pageable) {
        return movieService.getAllMovies (pageable);
    }

    @GetMapping("/{id}")
    public MovieResponseDto getMovieById(@PathVariable Long id) {
        return movieService.getMovieById (id);
    }

    @GetMapping("/title/{title}")
    public PaginatedResponseDto<MovieResponseDto> searchByTitle(@PathVariable String title , Pageable pageable) {
        return movieService.searchByTitle(title , pageable);
    }

    @PutMapping("/update/{id}")
    public MovieResponseDto updateMovie (@PathVariable Long id, @RequestBody @Valid MovieUpdateDto movieUpdateDto) {
        return movieService.updateMovie (id , movieUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie (id);
    }
}

package com.hagag.cineverse.controller;

import com.hagag.cineverse.dto.movie.MovieRequestDto;
import com.hagag.cineverse.dto.movie.MovieResponseDto;
import com.hagag.cineverse.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/create")
    public MovieResponseDto createMovie(@RequestBody @Valid MovieRequestDto movieRequestDto) {
        return movieService.createMovie (movieRequestDto);
    }
}

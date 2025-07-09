package com.hagag.cineverse.mapper;

import com.hagag.cineverse.dto.movie.MovieRequestDto;
import com.hagag.cineverse.dto.movie.MovieResponseDto;
import com.hagag.cineverse.entity.Movie;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface MovieMapper {

    MovieResponseDto toDto(Movie movie);

    Movie toEntity(MovieRequestDto movieRequestDto);
}

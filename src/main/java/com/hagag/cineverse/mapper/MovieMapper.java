package com.hagag.cineverse.mapper;

import com.hagag.cineverse.util.MovieMapperUtil;
import com.hagag.cineverse.dto.movie.MovieRequestDto;
import com.hagag.cineverse.dto.movie.MovieResponseDto;
import com.hagag.cineverse.dto.tmdb.TmdbMovieDto;
import com.hagag.cineverse.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper (componentModel = "spring", uses = MovieMapperUtil.class)
public interface MovieMapper {

    MovieResponseDto toDto(Movie movie);

    @Mapping(target = "id", ignore = true)
    Movie toEntity(MovieRequestDto movieRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tmdbId", source = "tmdbMovieDto.tmdbId")
    @Mapping(target = "title", source = "tmdbMovieDto.title")
    @Mapping(target = "overview", source = "tmdbMovieDto.description")
    @Mapping(target = "releaseDate", source = "tmdbMovieDto.releaseDate", qualifiedByName = "parseDate")
    @Mapping(target = "posterUrl", source = "tmdbMovieDto.posterPath", qualifiedByName = "buildPosterUrl")
    Movie toEntity(TmdbMovieDto tmdbMovieDto);

}

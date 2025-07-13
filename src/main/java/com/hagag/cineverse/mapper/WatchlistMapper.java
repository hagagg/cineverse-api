package com.hagag.cineverse.mapper;

import com.hagag.cineverse.dto.watchlist.WatchlistResponseDto;
import com.hagag.cineverse.entity.Watchlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WatchlistMapper {

    @Mapping(target = "movieId" , source = "movie.id")
    @Mapping(target = "userId" , source = "user.id")
    @Mapping(target = "movieTitle" , source = "movie.title")
    WatchlistResponseDto toDto(Watchlist watchlist);
}

package com.hagag.cineverse.mapper;

import com.hagag.cineverse.dto.watchlistitem.WatchlistItemResponseDto;
import com.hagag.cineverse.entity.WatchlistItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WatchlistItemMapper {

    @Mapping(target = "movieId" , source = "movie.id")
    @Mapping(target = "movieTitle" , source = "movie.title")
    WatchlistItemResponseDto toDto(WatchlistItem watchlistItem);
}

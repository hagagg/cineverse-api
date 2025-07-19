package com.hagag.cineverse.dto.watchlistitem;

import lombok.Data;

@Data
public class WatchlistItemResponseDto {

    private Long id;
    private Long movieId;
    private String movieTitle;

}

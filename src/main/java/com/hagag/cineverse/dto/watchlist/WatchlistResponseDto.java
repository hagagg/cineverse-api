package com.hagag.cineverse.dto.watchlist;

import lombok.Data;

@Data
public class WatchlistResponseDto {

    private Long id;
    private Long movieId;
    private String movieTitle;
    private Long userId;
}

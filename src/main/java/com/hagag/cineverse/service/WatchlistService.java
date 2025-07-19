package com.hagag.cineverse.service;

import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.dto.projection.TopWatchlistedMoviesDto;
import com.hagag.cineverse.dto.watchlistitem.WatchlistItemResponseDto;
import org.springframework.data.domain.Pageable;


public interface WatchlistService {

    WatchlistItemResponseDto addToWatchlist(Long movieId);

    void deleteMovieFromWatchlist(Long movieId);

    PaginatedResponseDto<WatchlistItemResponseDto> getMyWatchlist(Pageable pageable);

    void clearMyWatchlist();

    int getMyWatchlistCount();

    PaginatedResponseDto<TopWatchlistedMoviesDto> getTopWatchlistedMovies(Pageable pageable);
}

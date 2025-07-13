package com.hagag.cineverse.service;

import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.dto.projection.TopWatchlistedMoviesDto;
import com.hagag.cineverse.dto.watchlist.WatchlistResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface WatchlistService {

    WatchlistResponseDto addToWatchlist(Long movieId);

    void deleteMovieFromWatchlist(Long movieId);

    PaginatedResponseDto<WatchlistResponseDto> getMyWatchlist(Pageable pageable);

    void clearMyWatchlist();

    int getMyWatchlistCount();

    List<TopWatchlistedMoviesDto> getTopWatchlistedMovies(int limit);
}

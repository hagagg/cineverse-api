package com.hagag.cineverse.controller;

import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.dto.projection.TopWatchlistedMoviesDto;
import com.hagag.cineverse.dto.watchlist.WatchlistResponseDto;
import com.hagag.cineverse.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping("/{movieId}")
    public WatchlistResponseDto addToWatchlist (@PathVariable Long movieId) {
        return watchlistService.addToWatchlist (movieId);
    }

    @GetMapping
    public PaginatedResponseDto<WatchlistResponseDto> getMyWatchlist (Pageable pageable) {
        return watchlistService.getMyWatchlist (pageable);
    }

    @DeleteMapping("{movieId}")
    public void deleteMovieFromWatclist(@PathVariable Long movieId) {
        watchlistService.deleteMovieFromWatchlist (movieId);
    }

    @DeleteMapping("/clear")
    public void clearMyWatchlist () {
        watchlistService.clearMyWatchlist ();
    }

    @GetMapping("/count")
    public int getMyWatchlistCount () {
        return watchlistService.getMyWatchlistCount ();
    }

    @GetMapping("/top")
    public PaginatedResponseDto<TopWatchlistedMoviesDto> getTopWatchlistedMovies (Pageable pageable) {
        return watchlistService.getTopWatchlistedMovies (pageable);
    }

}

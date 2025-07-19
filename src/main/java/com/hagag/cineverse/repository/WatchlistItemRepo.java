package com.hagag.cineverse.repository;

import com.hagag.cineverse.dto.projection.TopWatchlistedMoviesDto;
import com.hagag.cineverse.entity.Movie;
import com.hagag.cineverse.entity.Watchlist;
import com.hagag.cineverse.entity.WatchlistItem;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WatchlistItemRepo extends JpaRepository<WatchlistItem, Long> {

    boolean existsByMovieAndWatchlist(Movie movie, Watchlist watchlist);

    Page<WatchlistItem> findAllByWatchlistOrderByAddedAtDesc(Watchlist watchlist, Pageable pageable);

    boolean existsByWatchlist(Watchlist watchlist);

    void deleteAllByWatchlist(Watchlist watchlist);

    Optional<WatchlistItem> findByMovieAndWatchlist(Movie movie, Watchlist watchlist);

    int countByWatchlist(@NotNull Watchlist watchlist);

    @Query("SELECT l.movie.id AS id , l.movie.title AS title, COUNT (l) AS watchlistCount " +
            "FROM WatchlistItem l GROUP BY l.movie.id , l.movie.title ORDER BY COUNT (l) DESC")
    Page<TopWatchlistedMoviesDto> findTopWatchlistedMovies(Pageable pageable);

}

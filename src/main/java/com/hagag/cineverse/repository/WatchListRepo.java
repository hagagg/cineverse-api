package com.hagag.cineverse.repository;

import com.hagag.cineverse.dto.projection.TopWatchlistedMoviesDto;
import com.hagag.cineverse.entity.Movie;
import com.hagag.cineverse.entity.User;
import com.hagag.cineverse.entity.Watchlist;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WatchListRepo extends JpaRepository<Watchlist, Long> {

    boolean existsByMovieAndUser( Movie movie, User user);

    Optional<Watchlist> findByMovieAndUser(Movie movie, User user);

    Page<Watchlist> findAllByUserOrderByAddedAtDesc(User user, Pageable pageable);

    boolean existsByUser(User user);

    void deleteAllByUser(@NotNull User user);

    int countByUser(User user);

    @Query ("SELECT l.movie.id AS id , l.movie.title AS title, COUNT (l) AS watchlistCount " +
             "FROM Watchlist l GROUP BY l.movie.id , l.movie.title ORDER BY COUNT (l) DESC")
    Page<TopWatchlistedMoviesDto> findTopWatchlistedMovies(Pageable pageable);
}

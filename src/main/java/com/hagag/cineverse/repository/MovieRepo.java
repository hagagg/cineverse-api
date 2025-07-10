package com.hagag.cineverse.repository;

import com.hagag.cineverse.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {

    boolean existsByTmdbId(Long tmdbMovieId);
}

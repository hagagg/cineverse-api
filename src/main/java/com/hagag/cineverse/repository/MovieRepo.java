package com.hagag.cineverse.repository;

import com.hagag.cineverse.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Long> {

    boolean existsByTmdbId(Long tmdbMovieId);

    @Query("""
    SELECT m FROM Movie m
    WHERE LOWER(REPLACE(m.title, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:title, ' ', ''), '%'))
""")
    List<Movie> findByTitleIgnoringSpacesAndCase(@Param("title") String title);
}

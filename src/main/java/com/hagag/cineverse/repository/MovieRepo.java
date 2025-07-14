package com.hagag.cineverse.repository;

import com.hagag.cineverse.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MovieRepo extends JpaRepository<Movie, Long> {

    boolean existsByTmdbId(Long tmdbMovieId);

    @Query("""
    SELECT m FROM Movie m
    WHERE LOWER(REPLACE(m.title, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:title, ' ', ''), '%'))
""")
    Page<Movie> findByTitleIgnoringSpacesAndCase(@Param("title") String title , Pageable pageable);
}

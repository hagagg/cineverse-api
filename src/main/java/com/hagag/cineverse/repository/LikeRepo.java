package com.hagag.cineverse.repository;

import com.hagag.cineverse.dto.projection.TopLikedMoviesDto;
import com.hagag.cineverse.entity.Like;
import com.hagag.cineverse.entity.Movie;
import com.hagag.cineverse.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;


public interface LikeRepo extends JpaRepository<Like, Long> {

    boolean existsByUserAndMovie(User user, Movie movie);

    Optional<Like> findByMovieAndUser(Movie movie, User user);

    List<Like> findAllByUser(User user);

    Long countByMovie(Movie movie);

    @Query("SELECT l.movie.id AS id , l.movie.title AS title , COUNT(l) AS likesCount " +
            "FROM Like l GROUP BY l.movie.id, l.movie.title ORDER BY COUNT(l) DESC")
    List<TopLikedMoviesDto> findTopLikedMovies(Pageable pageable);
}

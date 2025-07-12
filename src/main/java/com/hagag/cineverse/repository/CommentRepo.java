package com.hagag.cineverse.repository;

import com.hagag.cineverse.dto.comment.CommentResponseDto;
import com.hagag.cineverse.entity.Comment;
import com.hagag.cineverse.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByMovieId(Long movieId);

    List<Comment> findAllByUserId(Long userId);
}

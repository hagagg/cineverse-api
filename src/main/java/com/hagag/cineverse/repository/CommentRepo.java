package com.hagag.cineverse.repository;

import com.hagag.cineverse.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    Page<Comment> findCommentsByMovieId(Long movieId , Pageable pageable);

    Page<Comment> findAllByUserId(Long userId , Pageable pageable);
}

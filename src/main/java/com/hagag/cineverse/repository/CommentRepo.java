package com.hagag.cineverse.repository;

import com.hagag.cineverse.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}

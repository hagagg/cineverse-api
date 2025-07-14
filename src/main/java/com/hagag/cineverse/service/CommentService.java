package com.hagag.cineverse.service;

import com.hagag.cineverse.dto.comment.CommentRequestDto;
import com.hagag.cineverse.dto.comment.CommentResponseDto;
import com.hagag.cineverse.dto.comment.CommentUpdateDto;
import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import org.springframework.data.domain.Pageable;


public interface CommentService {

    CommentResponseDto addComment(CommentRequestDto commentRequestDto);

    PaginatedResponseDto<CommentResponseDto> getCommentsByMovieId(Long movieId , Pageable pageable);

    CommentResponseDto updateComment(Long commentId, CommentUpdateDto commentUpdateDto);

    void deleteComment(Long commentId);

    PaginatedResponseDto<CommentResponseDto> getCommentsByUserId(Long userId , Pageable pageable);
}

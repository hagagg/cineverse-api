package com.hagag.cineverse.service;

import com.hagag.cineverse.dto.comment.CommentRequestDto;
import com.hagag.cineverse.dto.comment.CommentResponseDto;
import com.hagag.cineverse.dto.comment.CommentUpdateDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto addComment(CommentRequestDto commentRequestDto);

    List<CommentResponseDto> getCommentsByMovieId(Long movieId);

    CommentResponseDto updateComment(Long commentId, CommentUpdateDto commentUpdateDto);

    void deleteComment(Long commentId);

    List<CommentResponseDto> getCommentsByUserId(Long userId);
}

package com.hagag.cineverse.controller;

import com.hagag.cineverse.dto.comment.CommentRequestDto;
import com.hagag.cineverse.dto.comment.CommentResponseDto;
import com.hagag.cineverse.dto.comment.CommentUpdateDto;
import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public CommentResponseDto addComment(@RequestBody @Valid CommentRequestDto commentRequestDto) {
        return commentService.addComment (commentRequestDto);
    }

    @GetMapping("/movie/{movieId}")
    public PaginatedResponseDto<CommentResponseDto> getCommentsByMovieId (@PathVariable Long movieId , Pageable pageable) {
        return commentService.getCommentsByMovieId (movieId , pageable);
    }

    @PutMapping("/update/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId ,@RequestBody @Valid CommentUpdateDto commentUpdateDto) {
        return commentService.updateComment (commentId , commentUpdateDto);
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment (commentId);
    }

    @GetMapping("/user/{userId}")
    public PaginatedResponseDto<CommentResponseDto> getCommentsByUserId(@PathVariable Long userId , Pageable pageable) {
        return commentService.getCommentsByUserId (userId , pageable);
    }
}

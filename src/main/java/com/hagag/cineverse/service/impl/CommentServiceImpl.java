package com.hagag.cineverse.service.impl;

import com.hagag.cineverse.dto.comment.CommentRequestDto;
import com.hagag.cineverse.dto.comment.CommentResponseDto;
import com.hagag.cineverse.dto.comment.CommentUpdateDto;
import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.entity.Comment;
import com.hagag.cineverse.entity.Movie;
import com.hagag.cineverse.entity.User;
import com.hagag.cineverse.exception.custom.ResourceNotFoundException;
import com.hagag.cineverse.mapper.CommentMapper;
import com.hagag.cineverse.mapper.PaginationMapper;
import com.hagag.cineverse.repository.CommentRepo;
import com.hagag.cineverse.repository.MovieRepo;
import com.hagag.cineverse.repository.UserRepo;
import com.hagag.cineverse.service.CommentService;
import com.hagag.cineverse.util.SecurityUtil;
import com.hagag.cineverse.validation.AccessGuard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final MovieRepo movieRepo;
    private final UserRepo userRepo;
    private final CommentMapper commentMapper;
    private final PaginationMapper paginationMapper;
    private final SecurityUtil securityUtil;
    private final AccessGuard accessGuard;


    @Override
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto) {
        User user = securityUtil.getCurrentUser();
        Movie movie = movieRepo.findById(commentRequestDto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id: " + commentRequestDto.getMovieId() + " not found"));

        Comment comment = commentMapper.toEntity(commentRequestDto);
        comment.setUser(user);
        comment.setMovie(movie);

        Comment savedComment = commentRepo.save(comment);

        return commentMapper.toDto(savedComment);
    }

    @Override
    public PaginatedResponseDto<CommentResponseDto> getCommentsByMovieId(Long movieId , Pageable pageable) {
        movieRepo.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Movie with id: " + movieId + " not found"));

        Page<Comment> commentPage = commentRepo.findCommentsByMovieId (movieId , pageable);
        Page<CommentResponseDto> dtoPage = commentPage.map(commentMapper::toDto);

        return paginationMapper.toPaginatedResponse(dtoPage);
    }

    @Override
    public CommentResponseDto updateComment(Long commentId, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment: " + commentId + " not found"));

        accessGuard.checkUserOnly(comment.getUser());

        comment.setContent(commentUpdateDto.getContent());

        commentRepo.save(comment);

        return commentMapper.toDto(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment: " + commentId + " not found"));

        accessGuard.checkUserOrAdmin(comment.getUser());

        commentRepo.delete(comment);
    }

    @Override
    public PaginatedResponseDto<CommentResponseDto> getCommentsByUserId(Long userId , Pageable pageable) {
        userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User wih id: " + userId + " not found"));

        accessGuard.checkAdminOnly();

        Page<Comment> comments = commentRepo.findAllByUserId (userId , pageable);
        Page<CommentResponseDto> dtoPage = comments.map(commentMapper::toDto);

        return paginationMapper.toPaginatedResponse(dtoPage);
    }
}

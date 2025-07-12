package com.hagag.cineverse.mapper;

import com.hagag.cineverse.dto.comment.CommentRequestDto;
import com.hagag.cineverse.dto.comment.CommentResponseDto;
import com.hagag.cineverse.entity.Comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "movieId" , source = "movie.id")
    @Mapping(target = "userId" , source = "user.id")
    @Mapping(target = "userFullName" , expression = "java(comment.getUser().getFullName())")
    CommentResponseDto toDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    Comment toEntity(CommentRequestDto commentRequestDto);
}

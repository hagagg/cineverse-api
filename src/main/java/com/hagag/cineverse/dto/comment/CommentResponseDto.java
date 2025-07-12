package com.hagag.cineverse.dto.comment;

import lombok.Data;

@Data
public class CommentResponseDto {
    private Long id;
    private String content;
    private Long movieId;
    private Long userId;
    private String userFullName;
}

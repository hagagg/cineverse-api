package com.hagag.cineverse.dto.like;

import lombok.Data;

@Data
public class LikeResponseDto {

    private Long id;
    private Long movieId;
    private Long userId;
    private String userFullName;
}

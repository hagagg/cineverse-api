package com.hagag.cineverse.service;

import com.hagag.cineverse.dto.like.LikeResponseDto;
import com.hagag.cineverse.dto.projection.TopLikedMoviesDto;

import java.util.List;

public interface LikeService {

    LikeResponseDto likeMovie(Long movieId);

    void unlikeMovie(Long movieId);

    List<LikeResponseDto> getAllLikesByUser(Long userId);

    Long getLikesCountForMovie(Long movieId);

    List<TopLikedMoviesDto> getMostLikedMovies(int limit);

    List<LikeResponseDto> getCurrentUserLikes();
}

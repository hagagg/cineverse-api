package com.hagag.cineverse.service;

import com.hagag.cineverse.dto.like.LikeResponseDto;
import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.dto.projection.TopLikedMoviesDto;
import org.springframework.data.domain.Pageable;


public interface LikeService {

    LikeResponseDto likeMovie(Long movieId);

    void unlikeMovie(Long movieId);

    PaginatedResponseDto<LikeResponseDto> getAllLikesByUser(Long userId , Pageable pageable);

    Long getLikesCountForMovie(Long movieId);

    PaginatedResponseDto<TopLikedMoviesDto> getMostLikedMovies(Pageable pageable);

    PaginatedResponseDto<LikeResponseDto> getCurrentUserLikes(Pageable pageable);
}

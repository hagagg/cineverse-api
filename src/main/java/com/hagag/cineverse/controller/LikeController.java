package com.hagag.cineverse.controller;

import com.hagag.cineverse.dto.like.LikeResponseDto;
import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.dto.projection.TopLikedMoviesDto;
import com.hagag.cineverse.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{movieId}")
    public LikeResponseDto likeMovie (@PathVariable Long movieId){
        return likeService.likeMovie (movieId);
    }

    @DeleteMapping("/{movieId}")
    public void unlikeMovie(@PathVariable Long movieId){
        likeService.unlikeMovie (movieId);
    }

    @GetMapping("/user/{userId}")
    public PaginatedResponseDto<LikeResponseDto> getAllLikesByUser (@PathVariable Long userId , Pageable pageable){
        return likeService.getAllLikesByUser (userId , pageable);
    }

    @GetMapping("/current")
    public PaginatedResponseDto<LikeResponseDto> getCuurentUserLikes (Pageable pageable){
        return likeService.getCurrentUserLikes (pageable);
    }

    @GetMapping("/movie/{movieId}")
    public Long getLikesCountForMovie (@PathVariable Long movieId){
        return likeService.getLikesCountForMovie (movieId);
    }

    @GetMapping("/top")
    public PaginatedResponseDto<TopLikedMoviesDto> getMostLikedMovies (Pageable pageable){
        return likeService.getMostLikedMovies(pageable);
    }
}

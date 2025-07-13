package com.hagag.cineverse.controller;

import com.hagag.cineverse.dto.like.LikeResponseDto;
import com.hagag.cineverse.dto.projection.TopLikedMoviesDto;
import com.hagag.cineverse.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<LikeResponseDto> getAllLikesByUser (@PathVariable Long userId){
        return likeService.getAllLikesByUser (userId);
    }

    @GetMapping("/current")
    public List<LikeResponseDto> getCuurentUserLikes (){
        return likeService.getCurrentUserLikes ();
    }

    @GetMapping("/movie/{movieId}")
    public Long getLikesCountForMovie (@PathVariable Long movieId){
        return likeService.getLikesCountForMovie (movieId);
    }

    @GetMapping("/top")
    public List<TopLikedMoviesDto> getMostLikedMovies (@RequestParam(defaultValue = "5") int limit){
        return likeService.getMostLikedMovies(limit);
    }
}

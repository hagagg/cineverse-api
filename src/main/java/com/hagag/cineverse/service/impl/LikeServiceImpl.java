package com.hagag.cineverse.service.impl;

import com.hagag.cineverse.dto.like.LikeResponseDto;
import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.dto.projection.TopLikedMoviesDto;
import com.hagag.cineverse.entity.Like;
import com.hagag.cineverse.entity.Movie;
import com.hagag.cineverse.entity.User;
import com.hagag.cineverse.exception.custom.AlreadyExistsException;
import com.hagag.cineverse.exception.custom.ResourceNotFoundException;
import com.hagag.cineverse.mapper.LikeMapper;
import com.hagag.cineverse.mapper.PaginationMapper;
import com.hagag.cineverse.repository.LikeRepo;
import com.hagag.cineverse.repository.MovieRepo;
import com.hagag.cineverse.repository.UserRepo;
import com.hagag.cineverse.service.LikeService;
import com.hagag.cineverse.util.SecurityUtil;
import com.hagag.cineverse.validation.AccessGuard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepo likeRepo;
    private final MovieRepo movieRepo;
    private final UserRepo userRepo;
    private final LikeMapper likeMapper;
    private final PaginationMapper paginationMapper;
    private final SecurityUtil securityUtil;
    private final AccessGuard accessGuard;

    @Override
    public LikeResponseDto likeMovie(Long movieId) {
        User user = securityUtil.getCurrentUser();
        Movie movie = movieRepo.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Movie with id: " + movieId +" not found"));

        if (likeRepo.existsByUserAndMovie (user , movie)) {
            throw new AlreadyExistsException("You have already liked the  movie with id: " + movieId);
        }
        Like like = Like.builder()
                .user(user)
                .movie(movie)
                .build();

        likeRepo.save(like);
        return likeMapper.toDto(like);
    }

    @Override
    public void unlikeMovie(Long movieId) {
        User user = securityUtil.getCurrentUser();
        Movie movie = movieRepo.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Movie with id: " + movieId +" not found"));
        Like like = likeRepo.findByMovieAndUser (movie , user).orElseThrow(() -> new ResourceNotFoundException("Like not found"));

        accessGuard.checkUserOnly(like.getUser());

        likeRepo.delete(like);
    }

    @Override
    public PaginatedResponseDto<LikeResponseDto> getAllLikesByUser(Long userId , Pageable pageable) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found"));

        Page<Like> page = likeRepo.findAllByUser(user , pageable);
        Page<LikeResponseDto> dtoPage = page.map(likeMapper::toDto);

        return paginationMapper.toPaginatedResponse(dtoPage);
    }

    @Override
    public PaginatedResponseDto<LikeResponseDto> getCurrentUserLikes(Pageable pageable) {
        User user = securityUtil.getCurrentUser();

        Page<Like> page = likeRepo.findAllByUser(user , pageable);
        Page<LikeResponseDto> dtoPage = page.map(likeMapper::toDto);

        return paginationMapper.toPaginatedResponse(dtoPage);
    }

    @Override
    public Long getLikesCountForMovie(Long movieId) {
        Movie movie = movieRepo.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Movie with id: " + movieId +" not found"));

        return likeRepo.countByMovie(movie);
    }

    @Override
    public PaginatedResponseDto<TopLikedMoviesDto> getMostLikedMovies(Pageable pageable) {

        Page<TopLikedMoviesDto> page = likeRepo.findTopLikedMovies(pageable);

        return paginationMapper.toPaginatedResponse(page);
    }

}

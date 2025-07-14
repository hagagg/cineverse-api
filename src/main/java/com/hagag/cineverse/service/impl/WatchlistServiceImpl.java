package com.hagag.cineverse.service.impl;

import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.dto.projection.TopWatchlistedMoviesDto;
import com.hagag.cineverse.dto.watchlist.WatchlistResponseDto;
import com.hagag.cineverse.entity.Movie;
import com.hagag.cineverse.entity.User;
import com.hagag.cineverse.entity.Watchlist;
import com.hagag.cineverse.exception.custom.AlreadyExistsException;
import com.hagag.cineverse.exception.custom.ResourceNotFoundException;
import com.hagag.cineverse.mapper.PaginationMapper;
import com.hagag.cineverse.mapper.WatchlistMapper;
import com.hagag.cineverse.repository.MovieRepo;
import com.hagag.cineverse.repository.WatchListRepo;
import com.hagag.cineverse.service.WatchlistService;
import com.hagag.cineverse.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService {

    private final WatchListRepo watchListRepo;
    private final MovieRepo movieRepo;
    private final WatchlistMapper watchlistMapper;
    private final PaginationMapper paginationMapper;
    private final SecurityUtil securityUtil;

    @Override
    public WatchlistResponseDto addToWatchlist(Long movieId) {
        User user = securityUtil.getCurrentUser();
        Movie movie = movieRepo.findById(movieId).orElseThrow(()-> new ResourceNotFoundException("Movie with id: " + movieId +" Not Found"));

        boolean exists = watchListRepo.existsByMovieAndUser(movie , user);

        if (exists) {
            throw new AlreadyExistsException("Movie with id: " + movieId +" Already Exists in the Watchlist");
        }

        Watchlist watchlist = Watchlist.builder()
                .movie(movie)
                .user(user)
                .build();

        watchListRepo.save(watchlist);

        return watchlistMapper.toDto(watchlist);
    }

    @Override
    public PaginatedResponseDto<WatchlistResponseDto> getMyWatchlist(Pageable pageable) {
        User user = securityUtil.getCurrentUser();

        Page<Watchlist> watchlistPage = watchListRepo.findAllByUserOrderByAddedAtDesc (user , pageable);

        Page<WatchlistResponseDto> dtoPage = watchlistPage.map(watchlistMapper::toDto);

        return paginationMapper.toPaginatedResponse(dtoPage);
    }

    @Override
    public void clearMyWatchlist() {
        User user = securityUtil.getCurrentUser();

        boolean exists = watchListRepo.existsByUser (user);

        if (!exists) {
            throw new ResourceNotFoundException("Your watchlist is already empty");
        }

        watchListRepo.deleteAllByUser(user);
    }

    @Override
    public void deleteMovieFromWatchlist(Long movieId) {
        User user = securityUtil.getCurrentUser();
        Movie movie = movieRepo.findById(movieId).orElseThrow(()-> new ResourceNotFoundException("Movie with id: " + movieId +" Not Found"));

        Watchlist watchlist = watchListRepo.findByMovieAndUser (movie , user)
                .orElseThrow(()-> new ResourceNotFoundException("Movie with id: " + movieId +" is not in Your Watchlist"));

        watchListRepo.delete(watchlist);
    }

    @Override
    public int getMyWatchlistCount() {
        User user = securityUtil.getCurrentUser();

        return watchListRepo.countByUser (user);
    }

    @Override
    public PaginatedResponseDto<TopWatchlistedMoviesDto> getTopWatchlistedMovies(Pageable pageable) {

        Page<TopWatchlistedMoviesDto> page = watchListRepo.findTopWatchlistedMovies(pageable);

        return paginationMapper.toPaginatedResponse(page);
    }


}

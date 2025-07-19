package com.hagag.cineverse.service.impl;

import com.hagag.cineverse.dto.pagination.PaginatedResponseDto;
import com.hagag.cineverse.dto.projection.TopWatchlistedMoviesDto;
import com.hagag.cineverse.dto.watchlistitem.WatchlistItemResponseDto;
import com.hagag.cineverse.entity.Movie;
import com.hagag.cineverse.entity.User;
import com.hagag.cineverse.entity.Watchlist;
import com.hagag.cineverse.entity.WatchlistItem;
import com.hagag.cineverse.exception.custom.AlreadyExistsException;
import com.hagag.cineverse.exception.custom.ResourceNotFoundException;
import com.hagag.cineverse.mapper.PaginationMapper;
import com.hagag.cineverse.mapper.WatchlistItemMapper;
import com.hagag.cineverse.repository.MovieRepo;
import com.hagag.cineverse.repository.WatchListRepo;
import com.hagag.cineverse.repository.WatchlistItemRepo;
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
    private final WatchlistItemRepo watchlistItemRepo;
    private final MovieRepo movieRepo;
    private final WatchlistItemMapper watchlistItemMapper;
    private final PaginationMapper paginationMapper;
    private final SecurityUtil securityUtil;

    @Override
    public WatchlistItemResponseDto addToWatchlist(Long movieId) {
        User user = securityUtil.getCurrentUser();
        Movie movie = movieRepo.findById(movieId).orElseThrow(()-> new ResourceNotFoundException("Movie with id: " + movieId +" Not Found"));

        Watchlist watchlist = watchListRepo.findByUser(user);

        boolean exists = watchlistItemRepo.existsByMovieAndWatchlist(movie , watchlist);

        if (exists) {
            throw new AlreadyExistsException("Movie with id: " + movieId +" Already Exists in the Watchlist");
        }

        WatchlistItem watchlistItem = WatchlistItem.builder()
                .movie(movie)
                .watchlist(watchlist)
                .build();

        watchlistItemRepo.save(watchlistItem);

        return watchlistItemMapper.toDto(watchlistItem);
    }

    @Override
    public PaginatedResponseDto<WatchlistItemResponseDto> getMyWatchlist(Pageable pageable) {
        User user = securityUtil.getCurrentUser();

        Watchlist watchlist = watchListRepo.findByUser(user);

        Page<WatchlistItem> itemsPage = watchlistItemRepo.findAllByWatchlistOrderByAddedAtDesc (watchlist , pageable);

        Page<WatchlistItemResponseDto> dtoPage = itemsPage.map(watchlistItemMapper::toDto);

        return paginationMapper.toPaginatedResponse(dtoPage);
    }

    @Override
    public void clearMyWatchlist() {
        User user = securityUtil.getCurrentUser();

        Watchlist watchlist = watchListRepo.findByUser(user);

        boolean hasItems = watchlistItemRepo.existsByWatchlist (watchlist);

        if (!hasItems) {
            throw new ResourceNotFoundException("Your watchlist is already empty");
        }

        watchlistItemRepo.deleteAllByWatchlist(watchlist);
    }

    @Override
    public void deleteMovieFromWatchlist(Long movieId) {
        User user = securityUtil.getCurrentUser();
        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(()-> new ResourceNotFoundException("Movie with id: " + movieId +" Not Found"));

        Watchlist watchlist = watchListRepo.findByUser (user);

        WatchlistItem item = watchlistItemRepo.findByMovieAndWatchlist(movie , watchlist)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id: " + movieId +" Not Found in your watchlist"));

        watchlistItemRepo.delete(item);
    }

    @Override
    public int getMyWatchlistCount() {
        User user = securityUtil.getCurrentUser();

        Watchlist watchlist = watchListRepo.findByUser(user);

        return watchlistItemRepo.countByWatchlist(watchlist);
    }

    @Override
    public PaginatedResponseDto<TopWatchlistedMoviesDto> getTopWatchlistedMovies(Pageable pageable) {

        Page<TopWatchlistedMoviesDto> page = watchlistItemRepo.findTopWatchlistedMovies(pageable);

        return paginationMapper.toPaginatedResponse(page);
    }


}

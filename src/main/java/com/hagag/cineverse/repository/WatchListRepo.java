package com.hagag.cineverse.repository;

import com.hagag.cineverse.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepo extends JpaRepository<Watchlist, Long> {

}

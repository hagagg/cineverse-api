package com.hagag.cineverse.client;

import com.hagag.cineverse.config.TmdbConfig;
import com.hagag.cineverse.dto.tmdb.TmdbMovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class TmdbClient {

    private final TmdbConfig tmdbConfig;
    private final WebClient webClient;

    public TmdbMovieDto fetchMovieById (Long tmdbId){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{id}")
                        .queryParam("api_key", tmdbConfig.getApiKey())
                        .build(tmdbId))
                .retrieve()
                .bodyToMono(TmdbMovieDto.class)
                .block();
    }
}

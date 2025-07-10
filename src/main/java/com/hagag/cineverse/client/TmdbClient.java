package com.hagag.cineverse.client;

import com.hagag.cineverse.config.TmdbConfig;
import com.hagag.cineverse.dto.tmdb.TmdbMovieDto;
import com.hagag.cineverse.exception.custom.TmdbException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TmdbClient {

    private final TmdbConfig tmdbConfig;
    private final WebClient webClient;

    public TmdbMovieDto fetchMovieById (Long tmdbId){
        // Send a GET request to TMDb
        return webClient.get()
                // Build full URL
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{id}")
                        .queryParam("api_key", tmdbConfig.getApiKey())
                        .build(tmdbId))

                // Execute the request
                .retrieve()

                // Handle 4xx client errors (e.g. 404 Not Found, 401 Unauthorized)
                .onStatus(status -> status.is4xxClientError() ,response ->
                        response.bodyToMono(String.class).defaultIfEmpty("Client Error")
                                .flatMap(errorBody ->Mono.error(
                                        new TmdbException("TMDb client error: " + errorBody))))
                // Handle 5xx server errors (e.g. 500 Internal Server Error)
                .onStatus(status -> status.is5xxServerError() ,response ->
                        response.bodyToMono(String.class).defaultIfEmpty("Server Error")
                                .flatMap(errorBody ->Mono.error(
                                        new TmdbException("TMDb server error: " + errorBody))))

                // Map JSON response to TmdbMovieDto
                .bodyToMono(TmdbMovieDto.class)

                // Makes it synchronous (waits for result)
                .block();
    }
}

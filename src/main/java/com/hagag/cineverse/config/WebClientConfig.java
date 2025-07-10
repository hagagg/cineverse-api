package com.hagag.cineverse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient tmdbWebClient(WebClient.Builder webClientBuilder , TmdbConfig tmdbConfig) {
        return webClientBuilder
                .baseUrl(tmdbConfig.getBaseUrl())
                .build();
    }
}

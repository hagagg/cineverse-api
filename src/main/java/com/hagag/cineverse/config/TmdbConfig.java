package com.hagag.cineverse.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties (prefix = "tmdb")
@Configuration
@Getter
@Setter
public class TmdbConfig {
    private String apiKey;
    private String baseUrl;
}

package com.hagag.cineverse.util;

import org.mapstruct.Named;

import java.time.LocalDate;

public class MovieMapperUtil {

    @Named("parseDate")
    public static LocalDate parseDate(String releaseDate) {
        return (releaseDate != null && !releaseDate.isBlank())
                ? LocalDate.parse(releaseDate)
                : null;
    }

    @Named("buildPosterUrl")
    public static String buildPosterUrl(String posterPath) {
        return (posterPath != null)
                ? "https://image.tmdb.org/t/p/w500" + posterPath
                : null;
    }
}
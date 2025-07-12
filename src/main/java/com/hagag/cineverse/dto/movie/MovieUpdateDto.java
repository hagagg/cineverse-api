package com.hagag.cineverse.dto.movie;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
public class MovieUpdateDto {

    @Size(max = 255)
    private String title;

    @Size(max = 500)
    @URL(message = "Poster must be a valid URL")
    private String posterUrl;

    private LocalDate releaseDate;

    private String overview;
}

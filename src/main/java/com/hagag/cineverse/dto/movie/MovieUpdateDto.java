package com.hagag.cineverse.dto.movie;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpdateDto {

    @Size(max = 255)
    private String title;

    @Size(max = 500)
    @URL(message = "Poster must be a valid URL")
    private String posterUrl;

    private LocalDate releaseDate;

    private String overview;
}

package com.hagag.cineverse.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "tmdb_id", nullable = false)
    private Long tmdbId;

    @Size(max = 255)
    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 500)
    @Column(name = "poster_url", length = 500)
    private String posterUrl;

    @NotNull
    @Column(name = "release_date" , nullable = false)
    private LocalDate releaseDate;

    @Lob
    @NotBlank
    @Column(name = "overview" , nullable = false)
    private String overview;

}
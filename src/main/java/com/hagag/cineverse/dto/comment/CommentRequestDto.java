package com.hagag.cineverse.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequestDto {
    @NotNull
    private Long movieId;
    @NotBlank
    private String content;
}

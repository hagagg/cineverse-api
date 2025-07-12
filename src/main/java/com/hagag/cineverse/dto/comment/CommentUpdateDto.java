package com.hagag.cineverse.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentUpdateDto {

    @NotBlank
    private String content;
}

package com.hagag.cineverse.mapper;

import com.hagag.cineverse.dto.like.LikeResponseDto;
import com.hagag.cineverse.entity.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    @Mapping(target = "movieId" , source = "movie.id")
    @Mapping(target = "userId" , source = "user.id")
    @Mapping(target = "userFullName" , expression = "java(like.getUser().getFullName())")
    LikeResponseDto toDto(Like like);
}

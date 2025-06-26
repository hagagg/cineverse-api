package com.hagag.cineverse.mapper;

import com.hagag.cineverse.dto.authentication.RegisterRequestDto;
import com.hagag.cineverse.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt" , ignore = true)
    @Mapping(target = "updatedAt" , ignore = true)
    @Mapping(target = "userName", source = "userName")
    User toEntity (RegisterRequestDto registerRequestDto);
}

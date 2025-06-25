package com.hagag.cineverse.mapper;

import com.hagag.cineverse.dto.user.UserResponseDto;
import com.hagag.cineverse.dto.user.UserSummaryDto;
import com.hagag.cineverse.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto (User user);

    UserSummaryDto toSummaryDto (User user);
}

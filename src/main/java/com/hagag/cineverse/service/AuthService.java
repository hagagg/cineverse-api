package com.hagag.cineverse.service;

import com.hagag.cineverse.dto.authentication.AuthResponseDto;
import com.hagag.cineverse.dto.authentication.LoginRequestDto;
import com.hagag.cineverse.dto.authentication.RegisterRequestDto;
import jakarta.validation.Valid;

public interface AuthService {
    AuthResponseDto register(@Valid RegisterRequestDto registerRequestDto);

    AuthResponseDto login(@Valid LoginRequestDto loginRequestDto);

}

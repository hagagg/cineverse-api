package com.hagag.cineverse.service;

import com.hagag.cineverse.dto.authentication.AuthResponseDto;
import com.hagag.cineverse.dto.authentication.LoginRequestDto;
import com.hagag.cineverse.dto.authentication.RegisterRequestDto;
import com.hagag.cineverse.entity.User;
import com.hagag.cineverse.enums.Role;
import com.hagag.cineverse.exception.custom.UserAlreadyExistsException;
import com.hagag.cineverse.mapper.AuthMapper;
import com.hagag.cineverse.repository.UserRepo;
import com.hagag.cineverse.security.JwtService;
import com.hagag.cineverse.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final AuthMapper authMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDto register(RegisterRequestDto registerRequestDto) {

        if (userRepo.findByUserName(registerRequestDto.getUserName()).isPresent()) {
            throw new UserAlreadyExistsException("Username: " + registerRequestDto.getUserName() + " already exists");
        }

        if (userRepo.findByEmail(registerRequestDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email: " + registerRequestDto.getEmail() + " already exists");
        }

        User user = authMapper.toEntity(registerRequestDto);
        user.setPassword(bCryptPasswordEncoder.encode(registerRequestDto.getPassword()));

        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        userRepo.save(user);
        String token = jwtService.generateToken(new UserPrincipal(user));

        return new AuthResponseDto(token);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }
}

package com.hagag.cineverse.service.impl;

import com.hagag.cineverse.dto.authentication.AuthResponseDto;
import com.hagag.cineverse.dto.authentication.LoginRequestDto;
import com.hagag.cineverse.dto.authentication.RegisterRequestDto;
import com.hagag.cineverse.entity.User;
import com.hagag.cineverse.enums.Role;
import com.hagag.cineverse.exception.custom.UserAlreadyExistsException;
import com.hagag.cineverse.exception.custom.UserNotFoundException;
import com.hagag.cineverse.mapper.AuthMapper;
import com.hagag.cineverse.repository.UserRepo;
import com.hagag.cineverse.security.JwtService;
import com.hagag.cineverse.security.UserPrincipal;
import com.hagag.cineverse.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final AuthMapper authMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto register(RegisterRequestDto registerRequestDto) {

        Optional<User> existingUser = userRepo.findByUserNameOrEmail(registerRequestDto.getUserName(), registerRequestDto.getEmail());
        if (existingUser.isPresent()) {
            User user = existingUser.get();

            if(user.getUserName().equals(registerRequestDto.getUserName())) {
                throw new UserAlreadyExistsException("User with username: " + registerRequestDto.getUserName() + ", already exists");
            }
            if(user.getEmail().equals(registerRequestDto.getEmail())) {
                throw new UserAlreadyExistsException("User with email: " + registerRequestDto.getEmail() + ", already exists");
            }
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

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmailOrUsername() , loginRequestDto.getPassword()));

        User user = userRepo.findByUserNameOrEmail(loginRequestDto.getEmailOrUsername() , loginRequestDto.getEmailOrUsername())
                .orElseThrow(()-> new UserNotFoundException("User with username or email: " + loginRequestDto.getEmailOrUsername() + " not found"));

        String token = jwtService.generateToken(new UserPrincipal(user));

        return new AuthResponseDto(token);
    }
}

package com.hagag.cineverse.exception;

import com.hagag.cineverse.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ApiErrorResponse handleUserAlreadyExists(UserAlreadyExistsException ex){
        return ApiErrorResponse.builder()
                .success(false)
                .message("User Already Exists")
                .details(ex.getMessage())
                .statusCode(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ApiErrorResponse handleUserNotFound(UserNotFoundException ex){
        return ApiErrorResponse.builder()
                .success(false)
                .message("User Not Found")
                .details(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleValidation (MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult().getFieldError() != null
                ? ex.getBindingResult().getFieldError().getField() + ": " + ex.getBindingResult().getFieldError().getDefaultMessage()
                : "Validation Failed";

        return ApiErrorResponse.builder()
                .success(false)
                .message("Validation Failed")
                .details(errorMessage)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(TmdbException.class)
    public ApiErrorResponse handleTmdbException (TmdbException ex){
        return ApiErrorResponse.builder()
                .success(false)
                .message("TMDb API Error")
                .details(ex.getMessage())
                .statusCode(HttpStatus.BAD_GATEWAY.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrorResponse handleResourceNotFound (ResourceNotFoundException ex){
        return ApiErrorResponse.builder()
                .success(false)
                .message("Resource Not Found")
                .details(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(UnauthorizedActionException.class)
    public ApiErrorResponse handleUnauthorized (UnauthorizedActionException ex) {
        return ApiErrorResponse.builder()
                .success(false)
                .message("Unauthorized Action")
                .details(ex.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .timestamp(LocalDateTime.now())
                .build();
    }
}

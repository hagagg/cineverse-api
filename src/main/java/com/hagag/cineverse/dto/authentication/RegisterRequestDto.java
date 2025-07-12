package com.hagag.cineverse.dto.authentication;

import com.hagag.cineverse.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @NotBlank(message = "Username is required")
    @Size(min =5, max =30, message = "Username must be between 5 and 30 characters")
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 30 characters")
    private String lastName;

    @NotBlank(message = "Password is required")
    @Size(min=8, message = "Password must be at least 8 characters long")
    private String password;

    private Role role;
}

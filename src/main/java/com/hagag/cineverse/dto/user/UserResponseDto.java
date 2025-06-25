package com.hagag.cineverse.dto.user;

import com.hagag.cineverse.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}

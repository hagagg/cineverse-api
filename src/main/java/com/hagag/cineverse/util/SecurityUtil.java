package com.hagag.cineverse.util;

import com.hagag.cineverse.entity.User;
import com.hagag.cineverse.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

    private final UserRepo userRepo;

    public String getCurrentUserName (){

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getCurrentUser (){

        String userName = getCurrentUserName();

        return userRepo.findByUserName(userName).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}

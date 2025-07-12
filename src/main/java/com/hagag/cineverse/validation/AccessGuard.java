package com.hagag.cineverse.validation;

import com.hagag.cineverse.entity.User;
import com.hagag.cineverse.enums.Role;
import com.hagag.cineverse.exception.custom.UnauthorizedActionException;
import com.hagag.cineverse.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessGuard {

    private final SecurityUtil securityUtil;

    public void checkUserOnly (User user) {
        User currentUser = securityUtil.getCurrentUser();

        if (!currentUser.getId().equals(user.getId())) {
            throw new UnauthorizedActionException("You are not allowed to do this action");
        }
    }

    public void checkUserOrAdmin (User user) {
        User currentUser = securityUtil.getCurrentUser();

        if (!currentUser.getId().equals(user.getId()) && !currentUser.getRole().equals(Role.ADMIN)) {
            throw new UnauthorizedActionException("You are not allowed to do this action");
        }
    }

    public void checkAdminOnly () {
        User currentUser = securityUtil.getCurrentUser();

        if (!currentUser.getRole().equals(Role.ADMIN)) {
            throw new UnauthorizedActionException("You are not allowed to do this action");
        }
    }

}

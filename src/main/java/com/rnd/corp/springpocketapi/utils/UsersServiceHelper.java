package com.rnd.corp.springpocketapi.utils;

import com.rnd.corp.springpocketapi.domain.ERole;
import com.rnd.corp.springpocketapi.service.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsersServiceHelper {

    /**
     * Check if the connected user has the admin role or if he is accessing to his own credentials
     *
     * @param login user's login
     * @return boolean
     */
    public static boolean checkUserOrigin(final String login) {
        final UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();

        final boolean hasRoleAdmin = user.getAuthorities()
                                         .stream()
                                         .map(Object::toString)
                                         .anyMatch(role -> role.equals(ERole.ROLE_ADMIN.toString()));

        return user.getLogin().equals(login) || hasRoleAdmin;
    }
}

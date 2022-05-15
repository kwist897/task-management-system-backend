package org.solowev.taskmanager.base.utils;

import org.solowev.taskmanager.base.exceptions.ErrorCode;
import org.solowev.taskmanager.base.exceptions.TaskManagerException;
import org.solowev.taskmanager.base.security.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class SecurityUtils {
    private SecurityUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static SecurityUser getCurrentUser() {
        SecurityUser principal = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal == null) {
            throw new TaskManagerException(ErrorCode.ACCESS_DENIED_EXCEPTION);
        }
        return principal;
    }

    @SuppressWarnings(value = "unchecked")
    public static boolean containsAnyRole(List<String> roles) {
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities();
        return grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(roles::contains);
    }
}

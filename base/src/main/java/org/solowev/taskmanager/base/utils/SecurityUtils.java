package org.solowev.taskmanager.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.solowev.taskmanager.base.exceptions.ErrorCode;
import org.solowev.taskmanager.base.exceptions.TaskManagerException;
import org.solowev.taskmanager.base.security.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.List;

@Slf4j
public class SecurityUtils {
    private SecurityUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static SecurityUser getCurrentUser() {
        SecurityUser principal = null;
        try {
            principal = (SecurityUser) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
        } catch (Exception e) {
            principal = SecurityUser.builder()
                    .id(-123L)
                    .username("UNAUTHENTICATED")
                    .build();
            log.warn("user is not authenticated");
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

package org.solowev.taskmanager.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.solowev.taskmanager.base.security.SecurityUser;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

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

package org.solowev.taskmanager.auth.service.impl;

import org.solowev.taskmanager.auth.security.SecurityUser;
import org.solowev.taskmanager.auth.service.HelperService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class HelperServiceImpl implements HelperService {
    @Override
    public SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}

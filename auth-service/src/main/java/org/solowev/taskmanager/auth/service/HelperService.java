package org.solowev.taskmanager.auth.service;

import org.solowev.taskmanager.auth.security.SecurityUser;

public interface HelperService {
    SecurityUser getCurrentUser();
}

package org.solowev.taskmanager.auth.service;

import org.solowev.taskmanager.base.security.SecurityUser;

public interface HelperService {
    SecurityUser getCurrentUser();
}

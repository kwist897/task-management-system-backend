package org.solowev.taskmanager.auth.service;

import org.solowev.taskmanager.auth.domain.User;
import org.solowev.taskmanager.auth.dto.response.TokenResponseDto;

import java.util.Map;

public interface TokenService {
    TokenResponseDto createTokens(User user);

    TokenResponseDto refreshToken();

    Map<String, Object> getJwkKeys();
}

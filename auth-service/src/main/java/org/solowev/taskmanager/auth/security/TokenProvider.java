package org.solowev.taskmanager.auth.security;

import com.nimbusds.jose.jwk.JWK;
import org.solowev.taskmanager.auth.domain.User;
import org.solowev.taskmanager.auth.dto.response.AccessTokenResponseDto;
import org.solowev.taskmanager.auth.dto.response.RefreshTokenResponseDto;

public interface TokenProvider {
    AccessTokenResponseDto generateAccessToken(User user);

    RefreshTokenResponseDto generateRefreshToken(User user);

    JWK getJWK();
}

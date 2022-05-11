package org.solowev.taskmanager.auth.service.impl;

import com.nimbusds.jose.jwk.JWKSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.solowev.taskmanager.base.security.CustomJwtAuthenticationToken;
import org.solowev.taskmanager.auth.domain.AccessToken;
import org.solowev.taskmanager.auth.domain.RefreshToken;
import org.solowev.taskmanager.auth.domain.User;
import org.solowev.taskmanager.auth.dto.response.AccessTokenResponseDto;
import org.solowev.taskmanager.auth.dto.response.RefreshTokenResponseDto;
import org.solowev.taskmanager.auth.dto.response.TokenResponseDto;
import org.solowev.taskmanager.auth.exceptions.NotFoundException;
import org.solowev.taskmanager.auth.exceptions.TokenException;
import org.solowev.taskmanager.auth.mapper.AccessTokenMapper;
import org.solowev.taskmanager.auth.mapper.RefreshTokenMapper;
import org.solowev.taskmanager.auth.repository.AccessTokenRepository;
import org.solowev.taskmanager.auth.repository.RefreshTokenRepository;
import org.solowev.taskmanager.auth.repository.UserRepository;
import org.solowev.taskmanager.auth.security.TokenProvider;
import org.solowev.taskmanager.auth.service.TokenService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {
    private final TokenProvider tokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    private final AccessTokenRepository accessTokenRepository;

    private final UserRepository userRepository;

    private final AccessTokenMapper accessTokenMapper;

    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public TokenResponseDto createTokens(User user) {
        TokenResponseDto tokenResponse = new TokenResponseDto();
        tokenResponse.setAccessToken(createAccessToken(user));
        tokenResponse.setRefreshToken(createRefreshToken(user));

        return tokenResponse;
    }

    @Override
    public TokenResponseDto refreshToken() {
        Jwt jwt = ((CustomJwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication())
                .getToken();
        String tokenId = jwt.getId();
        RefreshToken refreshToken = refreshTokenRepository.findById(tokenId)
                .orElseThrow(TokenException::new);

        User user = userRepository.findById(refreshToken.getUser().getId())
                .orElseThrow(NotFoundException::new);

        refreshTokenRepository.deleteById(tokenId);

        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setAccessToken(createAccessToken(user));
        tokenResponseDto.setRefreshToken(createRefreshToken(user));

        return tokenResponseDto;
    }

    @Override
    public Map<String, Object> getJwkKeys() {
        boolean onlyPublicKeys = true;
        return new JWKSet(tokenProvider.getJWK())
                .toJSONObject(onlyPublicKeys);
    }

    private AccessTokenResponseDto createAccessToken(User user) {
        AccessTokenResponseDto tokenResponse = tokenProvider.generateAccessToken(user);

        AccessToken accessToken = accessTokenMapper.toEntity(tokenResponse);
        accessToken.setUser(user);
        accessTokenRepository.save(accessToken);

        return tokenResponse;
    }

    private RefreshTokenResponseDto createRefreshToken(User user) {
        RefreshTokenResponseDto refreshTokenResponse = tokenProvider.generateRefreshToken(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setId(refreshTokenResponse.getId());
        refreshToken.setToken(refreshTokenResponse.getRefreshToken());
        refreshToken.setExpirationDate(refreshTokenResponse.getExpirationDate());
        refreshTokenRepository.save(refreshToken);

        return refreshTokenResponse;
    }
}

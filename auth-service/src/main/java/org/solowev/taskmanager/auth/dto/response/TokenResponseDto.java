package org.solowev.taskmanager.auth.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TokenResponseDto {
    private AccessTokenResponseDto accessToken;

    private RefreshTokenResponseDto refreshToken;
}

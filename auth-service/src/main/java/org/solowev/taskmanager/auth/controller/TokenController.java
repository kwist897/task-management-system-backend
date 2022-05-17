package org.solowev.taskmanager.auth.controller;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.auth.dto.response.TokenResponseDto;
import org.solowev.taskmanager.auth.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/token")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @GetMapping("/jwk/keys")
    public ResponseEntity<Map<String, Object>> getJWK() {
        return ResponseEntity.ok(tokenService.getJwkKeys());
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refreshToken() {
        return ResponseEntity.ok(tokenService.refreshToken());
    }
}

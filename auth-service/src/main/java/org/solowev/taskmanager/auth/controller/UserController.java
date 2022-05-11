package org.solowev.taskmanager.auth.controller;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.base.security.CustomJwtAuthenticationToken;
import org.solowev.taskmanager.auth.dto.request.UserRequestDto;
import org.solowev.taskmanager.auth.dto.response.UserResponseDto;
import org.solowev.taskmanager.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userService.registration(user));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponseDto> authUser(@RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userService.auth(user));
    }

    @GetMapping("/user/principal")
    public Object getToken(Authentication authentication){
        return authentication.getPrincipal();
    }

    @GetMapping("/user/token")
    public Object getElse(Authentication authentication){
        return ((CustomJwtAuthenticationToken) authentication).getToken();
    }
    @GetMapping("/user/roles")
    public Object getElse2(Authentication authentication){
        return authentication.getAuthorities();
    }
    @GetMapping("/user/creds")
    public Object getElse3(Authentication authentication){
        return authentication.getCredentials();
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponseDto> getCurrentUser() {
        return ResponseEntity.ok(userService.getUser());
    }
}

package org.solowev.taskmanager.auth.controller;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.auth.dto.request.UserRequestDto;
import org.solowev.taskmanager.auth.dto.response.UserResponseDto;
import org.solowev.taskmanager.auth.service.UserService;
import org.solowev.taskmanager.base.aspect.WebCallJoinPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @WebCallJoinPoint
    @PostMapping("/registration")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userService.registration(user));
    }

    @WebCallJoinPoint
    @PostMapping("/authenticate")
    public ResponseEntity<UserResponseDto> authUser(@RequestBody UserRequestDto user) {
        return ResponseEntity.ok(userService.auth(user));
    }

    @WebCallJoinPoint
    @GetMapping("/current")
    public ResponseEntity<UserResponseDto> getCurrentUser() {
        return ResponseEntity.ok(userService.getUser());
    }
}

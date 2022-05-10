package org.solowev.taskmanager.auth.service;

import org.solowev.taskmanager.auth.dto.request.UserRequestDto;
import org.solowev.taskmanager.auth.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto registration(UserRequestDto user);

    UserResponseDto auth(UserRequestDto user);

    UserResponseDto getUser();
}

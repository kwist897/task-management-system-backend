package org.solowev.taskmanager.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.solowev.taskmanager.auth.dto.request.UserRequestDto;
import org.solowev.taskmanager.auth.dto.response.UserResponseDto;
import org.solowev.taskmanager.base.model.dto.ProfileResponseDto;

public interface UserService {
    UserResponseDto registration(UserRequestDto user);

    UserResponseDto auth(UserRequestDto user);

    UserResponseDto getUser();

    UserResponseDto getProfileByUsername(String username) throws JsonProcessingException;
}

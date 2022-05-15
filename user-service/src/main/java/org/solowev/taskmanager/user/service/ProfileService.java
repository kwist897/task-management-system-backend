package org.solowev.taskmanager.user.service;

import org.solowev.taskmanager.user.dto.request.ProfileRequestDto;
import org.solowev.taskmanager.user.dto.response.ProfileResponseDto;

public interface ProfileService {
    ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto);

    ProfileResponseDto getProfileByUserId(Long userId);

    ProfileResponseDto updateProfile(ProfileResponseDto profileResponseDto);
}

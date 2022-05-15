package org.solowev.taskmanager.user.service;

import org.solowev.taskmanager.user.domain.Profile;
import org.solowev.taskmanager.user.dto.request.ProfileRequestDto;
import org.solowev.taskmanager.user.dto.response.ProfileResponseDto;

public interface ProfileService {
    ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto);

    ProfileResponseDto getCurrentUserProfile();

    ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto, Long profileId);

    Profile findProfileById(Long profileId);

    Profile findProfileByUserId(Long userId);
}

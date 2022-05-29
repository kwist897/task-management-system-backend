package org.solowev.taskmanager.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.solowev.taskmanager.base.utils.SecurityUtils;
import org.solowev.taskmanager.user.domain.Profile;
import org.solowev.taskmanager.user.dto.request.ProfileRequestDto;
import org.solowev.taskmanager.user.dto.response.ProfileResponseDto;
import org.solowev.taskmanager.user.exception.NotFoundException;
import org.solowev.taskmanager.user.exception.ProfileNotFoundException;
import org.solowev.taskmanager.user.mapper.ProfileRequestMapper;
import org.solowev.taskmanager.user.mapper.ProfileResponseMapper;
import org.solowev.taskmanager.user.repository.ProfileRepository;
import org.solowev.taskmanager.user.service.ProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    private final ProfileRequestMapper profileRequestMapper;

    private final ProfileResponseMapper profileResponseMapper;

    @Override
    public ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto) {
        Long userId = SecurityUtils.getCurrentUser().getId();

        Profile profile = profileRequestMapper.toEntity(profileRequestDto);
        profile.setUserId(userId);
        profileRepository.save(profile);

        log.info("created profile {}", profile);
        log.info("");

        return profileResponseMapper.toDto(profile);
    }

    @Override
    public ProfileResponseDto getCurrentUserProfile() {
        Long userId = SecurityUtils.getCurrentUser().getId();
        Profile profile = findProfileByUserId(userId);
        return profileResponseMapper.toDto(profile);
    }

    @Override
    public ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto, Long profileId) {
        Profile profile = findProfileById(profileId);

        profileRequestMapper.updateEntityByRequestDto(profile, profileRequestDto);

        return profileResponseMapper.toDto(profileRepository.save(profile));
    }

    @Override
    public Profile findProfileById(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException(profileId));
    }

    @Override
    public Profile findProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("couldn't find profile for current user"));
    }

    @Override
    public ProfileResponseDto getProfileByUserId(Long userId) {
        Profile profile = findProfileByUserId(userId);
        return profileResponseMapper.toDto(profile);
    }
}

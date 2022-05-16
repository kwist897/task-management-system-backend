package org.solowev.taskmanager.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.solowev.taskmanager.base.aspect.WebCallJoinPoint;
import org.solowev.taskmanager.user.dto.request.ProfileRequestDto;
import org.solowev.taskmanager.user.dto.response.ProfileResponseDto;
import org.solowev.taskmanager.user.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final ProfileService profileService;

    @WebCallJoinPoint
    @PostMapping("/profile")
    public ResponseEntity<ProfileResponseDto> createProfile(@RequestBody ProfileRequestDto profileRequestDto) {
        return ResponseEntity.ok(profileService.createProfile(profileRequestDto));
    }

    @WebCallJoinPoint
    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDto> getCurrentUserProfile() {
        return ResponseEntity.ok(profileService.getCurrentUserProfile());
    }

    @WebCallJoinPoint
    @PutMapping("/profile/{profileId}")
    public ResponseEntity<ProfileResponseDto> updateProfile(@RequestBody ProfileRequestDto profileRequestDto,
                                                            @PathVariable("profileId") Long profileId) {
        return ResponseEntity.ok(profileService.updateProfile(profileRequestDto, profileId));
    }
}

package org.solowev.taskmanager.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.solowev.taskmanager.base.exceptions.ErrorCode;
import org.solowev.taskmanager.base.exceptions.TaskManagerException;
import org.solowev.taskmanager.base.security.SecurityUser;
import org.solowev.taskmanager.base.utils.SecurityUtils;
import org.solowev.taskmanager.user.domain.Group;
import org.solowev.taskmanager.user.domain.Profile;
import org.solowev.taskmanager.user.dto.request.GroupRequestDto;
import org.solowev.taskmanager.user.dto.response.GroupResponseDto;
import org.solowev.taskmanager.user.exception.GroupNotFoundException;
import org.solowev.taskmanager.user.mapper.GroupRequestMapper;
import org.solowev.taskmanager.user.mapper.GroupResponseMapper;
import org.solowev.taskmanager.user.repository.GroupRepository;
import org.solowev.taskmanager.user.repository.ProfileRepository;
import org.solowev.taskmanager.user.service.GroupService;
import org.solowev.taskmanager.user.service.ProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GroupServiceImpl implements GroupService {
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    private final GroupRepository groupRepository;

    private final GroupResponseMapper groupResponseMapper;

    private final GroupRequestMapper groupRequestMapper;

    private final ProfileService profileService;

    private final ProfileRepository profileRepository;

    @Override
    public GroupResponseDto createGroup(GroupRequestDto groupRequestDto) {
        if (groupRequestDto.getTitle() == null || groupRequestDto.getIsPrivate() == null) {
            throw new TaskManagerException(ErrorCode.BAD_REQUEST, "title or visibility is null");
        }
        Group group = groupRequestMapper.toEntity(groupRequestDto);
        SecurityUser user = SecurityUtils.getCurrentUser();

        Profile profile = profileRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Profile newProfile = new Profile();
                    newProfile.setUserId(user.getId());
                    newProfile.setFirstName(user.getUsername());
                    return profileRepository.save(newProfile);
                });

        group.setCreatedBy(profile);
        group.setUpdatedBy(profile);
        group.setParticipants(Set.of(profile));

        groupRepository.save(group);

        return groupResponseMapper.toDto(group);
    }

    @Override
    public List<GroupResponseDto> getUserGroups(Long profileId) {
        List<Group> groups = groupRepository.findByParticipants_Id(profileId);

        List<Group> availableGroups = groups.stream()
                .filter(group -> BooleanUtils.isNotTrue(group.getIsPrivate())
                        || SecurityUtils.containsAnyRole(List.of(ROLE_ADMIN)))
                .toList();

        return groupResponseMapper.toDto(availableGroups);
    }

    @Override
    public GroupResponseDto updateGroup(GroupRequestDto groupRequestDto, Long groupId) {
        Group group = findGroupById(groupId);

        Long userId = SecurityUtils.getCurrentUser().getId();
        Profile profile = profileService.findProfileByUserId(userId);

        if (!group.getParticipants().contains(profile) && !SecurityUtils.containsAnyRole(List.of(ROLE_ADMIN))) {
            throw new TaskManagerException(ErrorCode.ACCESS_DENIED_EXCEPTION);
        }

        groupRequestMapper.updateEntityByRequestDto(group, groupRequestDto);
        group.setUpdatedBy(profile);
        groupRepository.save(group);

        return groupResponseMapper.toDto(group);
    }

    @Override
    public GroupResponseDto addParticipant(Long profileId, Long groupId) {
        Group group = findGroupById(groupId);

        Profile profile = profileService.findProfileById(profileId);

        Long userId = SecurityUtils.getCurrentUser().getId();
        Profile currentProfile = profileService.findProfileByUserId(userId);

        if (group.getParticipants().contains(profile) || !group.getParticipants().contains(currentProfile)) {
            throw new TaskManagerException(ErrorCode.BAD_REQUEST,
                    "you are trying to add existing participant or you are not in the group");
        }

        group.addParticipant(profile);
        group.setUpdatedBy(currentProfile);
        groupRepository.save(group);

        return groupResponseMapper.toDto(group);
    }

    @Override
    public GroupResponseDto deleteParticipant(Long profileId, Long groupId) {
        Group group = findGroupById(groupId);

        Profile profile = profileService.findProfileById(profileId);

        Long userId = SecurityUtils.getCurrentUser().getId();
        Profile currentProfile = profileService.findProfileByUserId(userId);

        if (!group.getParticipants().containsAll(List.of(profile, currentProfile))) {
            throw new TaskManagerException(ErrorCode.BAD_REQUEST,
                    "profile to delete or current profile is not in the group");
        }

        group.removeParticipant(profile);
        group.setUpdatedBy(currentProfile);
        groupRepository.save(group);

        return groupResponseMapper.toDto(group);
    }

    private Group findGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }
}

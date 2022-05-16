package org.solowev.taskmanager.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.solowev.taskmanager.base.exceptions.ErrorCode;
import org.solowev.taskmanager.base.exceptions.TaskManagerException;
import org.solowev.taskmanager.base.utils.SecurityUtils;
import org.solowev.taskmanager.user.domain.Group;
import org.solowev.taskmanager.user.domain.Profile;
import org.solowev.taskmanager.user.domain.Workspace;
import org.solowev.taskmanager.user.domain.enums.WorkspaceType;
import org.solowev.taskmanager.user.dto.request.WorkspaceRequestDto;
import org.solowev.taskmanager.user.dto.response.UserWorkspacesResponseDto;
import org.solowev.taskmanager.user.dto.response.WorkspaceResponseDto;
import org.solowev.taskmanager.user.exception.GroupNotFoundException;
import org.solowev.taskmanager.user.exception.WorkspaceNotFoundException;
import org.solowev.taskmanager.user.mapper.WorkspaceRequestMapper;
import org.solowev.taskmanager.user.mapper.WorkspaceResponseMapper;
import org.solowev.taskmanager.user.repository.GroupRepository;
import org.solowev.taskmanager.user.repository.WorkspaceRepository;
import org.solowev.taskmanager.user.service.ProfileService;
import org.solowev.taskmanager.user.service.WorkspaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;

    private final WorkspaceRequestMapper workspaceRequestMapper;

    private final WorkspaceResponseMapper workspaceResponseMapper;

    private final GroupRepository groupRepository;

    private final ProfileService profileService;

    @Override
    public WorkspaceResponseDto createWorkspace(WorkspaceRequestDto workspaceRequestDto) {
        Workspace workspace = workspaceRequestMapper.toEntity(workspaceRequestDto);

        if (workspace.getWorkspaceType() == WorkspaceType.GROUP_WORKSPACE
                && workspaceRequestDto.getGroupId() != null) {
            Group group = groupRepository.findById(workspaceRequestDto.getGroupId())
                    .orElseThrow(() -> new GroupNotFoundException(workspaceRequestDto.getGroupId()));

            workspace.setGroup(group);
        }

        Long userId = SecurityUtils.getCurrentUser().getId();
        workspace.setCreatedBy(profileService.findProfileByUserId(userId));

        workspaceRepository.save(workspace);

        return workspaceResponseMapper.toDto(workspace);
    }

    @Override
    public UserWorkspacesResponseDto getAllWorkspaces() {
        Long userID = SecurityUtils.getCurrentUser().getId();
        Profile profile = profileService.findProfileByUserId(userID);

        List<Long> groupIds = profile.getGroups().stream()
                .map(Group::getId)
                .toList();

        List<Workspace> workspaces = workspaceRepository.findAllByProfileIdOrGroupIds(profile.getId(), groupIds);

        List<WorkspaceResponseDto> workspaceResponses = workspaceResponseMapper.toDto(workspaces);

        Map<WorkspaceType, List<WorkspaceResponseDto>> workspaceMap = workspaceResponses.stream()
                .collect(Collectors.groupingBy(WorkspaceResponseDto::getWorkspaceType));

        UserWorkspacesResponseDto response = new UserWorkspacesResponseDto();
        response.setPersonalWorkspaces(workspaceMap.get(WorkspaceType.PERSONAL));
        response.setGroupWorkspaces(workspaceMap.get(WorkspaceType.GROUP_WORKSPACE));

        return response;
    }

    @Override
    public List<WorkspaceResponseDto> getWorkspacesByGroupId(Long groupId) {
        Long userId = SecurityUtils.getCurrentUser().getId();
        Profile profile = profileService.findProfileByUserId(userId);

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));

        if (!group.getParticipants().contains(profile)) {
            throw new TaskManagerException(ErrorCode.ACCESS_DENIED_EXCEPTION);
        }

        List<Workspace> workspaces = workspaceRepository.findAllByGroup_Id(groupId);

        return workspaceResponseMapper.toDto(workspaces);
    }

    @Override
    public WorkspaceResponseDto updateWorkspace(WorkspaceRequestDto workspaceRequestDto, Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new WorkspaceNotFoundException(workspaceId));

        Long userId = SecurityUtils.getCurrentUser().getId();

        Profile profile = profileService.findProfileByUserId(userId);

        if (workspace.getWorkspaceType() == WorkspaceType.GROUP_WORKSPACE
                && !workspace.getGroup().getParticipants().contains(profile)) {
            throw new TaskManagerException(ErrorCode.ACCESS_DENIED_EXCEPTION);
        }

        workspaceRequestMapper.updateEntityByRequestDto(workspace, workspaceRequestDto);

        workspaceRepository.save(workspace);

        return workspaceResponseMapper.toDto(workspace);
    }
}

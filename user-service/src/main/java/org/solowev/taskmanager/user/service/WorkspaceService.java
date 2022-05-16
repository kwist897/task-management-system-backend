package org.solowev.taskmanager.user.service;

import org.solowev.taskmanager.user.dto.request.WorkspaceRequestDto;
import org.solowev.taskmanager.user.dto.response.UserWorkspacesResponseDto;
import org.solowev.taskmanager.user.dto.response.WorkspaceResponseDto;

import java.util.List;

public interface WorkspaceService {
    WorkspaceResponseDto createWorkspace(WorkspaceRequestDto workspaceRequestDto);

    UserWorkspacesResponseDto getAllWorkspaces();

    List<WorkspaceResponseDto> getWorkspacesByGroupId(Long groupId);

    WorkspaceResponseDto updateWorkspace(WorkspaceRequestDto workspaceRequestDto, Long workspaceId);
}

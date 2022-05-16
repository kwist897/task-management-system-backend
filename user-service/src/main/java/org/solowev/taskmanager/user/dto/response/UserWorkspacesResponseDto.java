package org.solowev.taskmanager.user.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserWorkspacesResponseDto {
    private List<WorkspaceResponseDto> personalWorkspaces;

    private List<WorkspaceResponseDto> groupWorkspaces;
}

package org.solowev.taskmanager.user.dto.response;

import lombok.Data;
import org.solowev.taskmanager.user.domain.enums.WorkspaceType;

@Data
public class WorkspaceResponseDto {
    private Long id;

    private String title;

    private String description;

    private ProfileResponseDto createdBy;

    private WorkspaceType workspaceType;

    private GroupResponseDto group;
}

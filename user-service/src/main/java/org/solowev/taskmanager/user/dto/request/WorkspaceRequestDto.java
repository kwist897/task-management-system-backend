package org.solowev.taskmanager.user.dto.request;

import lombok.Data;
import org.solowev.taskmanager.user.domain.enums.WorkspaceType;

@Data
public class WorkspaceRequestDto {
    private String title;

    private String description;

    private WorkspaceType workspaceType;

    private Long groupId;
}

package org.solowev.taskmanager.user.dto.response;

import lombok.Data;

@Data
public class WorkspaceResponseDto {
    private Long id;

    private String title;

    private String description;

    private ProfileResponseDto createdBy;

    private GroupResponseDto group;
}

package org.solowev.taskmanager.user.dto.request;

import lombok.Data;
import org.solowev.taskmanager.user.dto.response.GroupResponseDto;
import org.solowev.taskmanager.user.dto.response.ProfileResponseDto;

@Data
public class WorkspaceRequestDto {
    private Long id;

    private String title;

    private String description;

    private ProfileResponseDto createdBy;

    private GroupResponseDto group;
}

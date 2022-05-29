package org.solowev.taskmanager.user.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.solowev.taskmanager.base.model.BaseDto;
import org.solowev.taskmanager.user.domain.enums.WorkspaceType;

@EqualsAndHashCode(callSuper = true)
@Data
public class WorkspaceResponseDto extends BaseDto {
    private Long id;

    private String title;

    private String description;

    private ProfileResponseDto createdBy;

    private WorkspaceType workspaceType;

    private GroupResponseDto group;
}

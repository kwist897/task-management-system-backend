package org.solowev.taskmanager.user.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.solowev.taskmanager.base.model.BaseDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserWorkspacesResponseDto extends BaseDto {
    private List<WorkspaceResponseDto> personalWorkspaces;

    private List<WorkspaceResponseDto> groupWorkspaces;
}

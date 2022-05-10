package org.solowev.taskmanager.auth.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.solowev.taskmanager.auth.dto.BaseDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class WorkspaceResponseDto extends BaseDto {
    private Long id;
    private String title;
    private String description;
    private List<UserResponseDto> users;
}

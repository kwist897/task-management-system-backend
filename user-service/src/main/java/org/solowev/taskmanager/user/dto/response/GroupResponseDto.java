package org.solowev.taskmanager.user.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.solowev.taskmanager.base.model.BaseDto;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupResponseDto extends BaseDto {
    private Long id;

    private String title;

    private String description;

    private Boolean isPrivate;

    private ProfileResponseDto createdBy;

    private ProfileResponseDto updatedBy;

    private Set<ProfileResponseDto> participants;
}

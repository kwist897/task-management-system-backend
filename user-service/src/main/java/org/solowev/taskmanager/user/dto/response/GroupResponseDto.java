package org.solowev.taskmanager.user.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class GroupResponseDto {
    private Long id;

    private String title;

    private String description;

    private Boolean isPrivate;

    private ProfileResponseDto createdBy;

    private ProfileResponseDto updatedBy;

    private Set<ProfileResponseDto> participants;
}

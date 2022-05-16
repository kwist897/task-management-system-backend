package org.solowev.taskmanager.user.dto.request;

import lombok.Data;

@Data
public class GroupRequestDto {
    private String title;

    private String description;

    private Boolean isPrivate;
}

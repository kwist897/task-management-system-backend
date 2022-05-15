package org.solowev.taskmanager.user.dto.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class GroupRequestDto {
    @NonNull
    private String title;

    private String description;

    @NonNull
    private Boolean isPrivate;
}

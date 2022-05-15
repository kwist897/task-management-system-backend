package org.solowev.taskmanager.user.dto.response;

import lombok.Data;

@Data
public class ProfileResponseDto {
    private Long id;

    private String firstName;

    private String lastName;

    private Long userId;
}

package org.solowev.taskmanager.user.dto.request;

import lombok.Data;

@Data
public class ProfileRequestDto {
    private Long id;

    private String firstName;

    private String lastName;
}

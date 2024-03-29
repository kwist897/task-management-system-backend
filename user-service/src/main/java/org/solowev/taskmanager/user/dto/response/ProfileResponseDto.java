package org.solowev.taskmanager.user.dto.response;

import lombok.Data;
import org.solowev.taskmanager.base.model.BaseDto;

@Data
public class ProfileResponseDto extends BaseDto {
    private Long id;

    private String firstName;

    private String lastName;
}

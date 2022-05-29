package org.solowev.taskmanager.base.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.solowev.taskmanager.base.model.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfileResponseDto extends BaseDto {
    private Long id;

    private String firstName;

    private String lastName;
}

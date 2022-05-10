package org.solowev.taskmanager.auth.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.solowev.taskmanager.auth.dto.BaseDto;
import org.solowev.taskmanager.auth.utils.enums.RoleEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleResponseDto extends BaseDto {
    private Long id;
    private RoleEnum roleName;
}

package org.solowev.taskmanager.auth.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.solowev.taskmanager.auth.utils.enums.RoleEnum;
import org.solowev.taskmanager.base.model.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleResponseDto extends BaseDto {
    private Long id;

    private RoleEnum roleName;
}

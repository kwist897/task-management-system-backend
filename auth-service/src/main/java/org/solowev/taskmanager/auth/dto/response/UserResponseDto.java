package org.solowev.taskmanager.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.solowev.taskmanager.auth.dto.BaseDto;
import org.solowev.taskmanager.auth.utils.enums.AccountType;
import org.solowev.taskmanager.auth.utils.enums.AuthProvider;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
public class UserResponseDto extends BaseDto {
    private Long id;
    private String username;
    private Boolean enabled;
    private String email;
    private AuthProvider authProvider;
    private AccountType accountType;
    private List<RoleResponseDto> roles;
    private TokenResponseDto tokens;
}

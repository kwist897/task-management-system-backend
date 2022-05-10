package org.solowev.taskmanager.auth.dto.request;

import lombok.Data;
import org.solowev.taskmanager.auth.utils.enums.AccountType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRequestDto {
    @Size(min = 3, max = 64)
    @NotNull
    private String username;

    @Size(min = 5)
    @NotNull
    private String password;

    private String email;

    private AccountType accountType;
}

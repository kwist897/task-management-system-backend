package org.solowev.taskmanager.auth.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SecurityUser {
    private Long id;
    private String username;
    private String email;
}

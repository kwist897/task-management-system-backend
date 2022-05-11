package org.solowev.taskmanager.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RefreshTokenResponseDto {
    @JsonIgnore
    private String id;

    private String refreshToken;

    private Date expirationDate;
}

package org.solowev.taskmanager.auth.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class JwkResponseDto extends BaseDto {
    private Map<String, String> keys;
}

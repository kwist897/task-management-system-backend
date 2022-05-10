package org.solowev.taskmanager.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {
    private ResponseResult result;
    private ErrorDto error;
    private Object data;
}

package org.solowev.taskmanager.auth.dto;

import lombok.Data;

@Data
public class ErrorDto {
    private String text;
    private String code;
}

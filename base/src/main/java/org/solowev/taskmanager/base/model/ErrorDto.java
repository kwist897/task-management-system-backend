package org.solowev.taskmanager.base.model;

import lombok.Data;

@Data
public class ErrorDto {
    private String text;
    private String code;
}

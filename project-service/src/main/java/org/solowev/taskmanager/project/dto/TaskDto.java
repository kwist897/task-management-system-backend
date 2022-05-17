package org.solowev.taskmanager.project.dto;

import lombok.Data;
import org.solowev.taskmanager.project.enums.TaskStatusEnum;
import org.solowev.taskmanager.project.enums.TaskTypeEnum;

@Data
public class TaskDto {
    private Long id;
    private TaskTypeEnum type;
    private String subject;
    private String text;
    private Long interval;
    private ProjectDto project;
    private ProfileResponseDto owner;
    private ProfileResponseDto executor;
    private TaskStatusEnum status;
}

package org.solowev.taskmanager.project.dto;

import lombok.Data;
import org.solowev.taskmanager.project.enums.TaskTypeEnum;

@Data
public class TaskRequestDto {
    private Long id;

    private TaskTypeEnum type;

    private String subject;

    private String text;

    private Long interval;

    private Long executorId;

    private Long ownerId;

    private Long projectId;
}

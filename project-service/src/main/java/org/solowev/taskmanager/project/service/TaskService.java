package org.solowev.taskmanager.project.service;

import org.solowev.taskmanager.project.dto.TaskDto;
import org.solowev.taskmanager.project.dto.TaskRequestDto;
import org.solowev.taskmanager.project.enums.TaskStatusEnum;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskRequestDto task);

    TaskDto updateTask(TaskRequestDto task);

    TaskDto changeStatus(TaskRequestDto task, TaskStatusEnum taskStatus);

    List<TaskDto> getByProject(Long projectId);

    List<TaskDto> getAllTasksByUser();

    void deleteTask(Long taskId);

    TaskDto getById(Long taskId);
}

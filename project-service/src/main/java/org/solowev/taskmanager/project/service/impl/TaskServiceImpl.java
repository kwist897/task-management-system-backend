package org.solowev.taskmanager.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.base.exceptions.ErrorCode;
import org.solowev.taskmanager.base.exceptions.TaskManagerException;
import org.solowev.taskmanager.base.utils.SecurityUtils;
import org.solowev.taskmanager.project.domain.Project;
import org.solowev.taskmanager.project.domain.Task;
import org.solowev.taskmanager.project.dto.TaskDto;
import org.solowev.taskmanager.project.dto.TaskRequestDto;
import org.solowev.taskmanager.project.enums.TaskStatusEnum;
import org.solowev.taskmanager.project.mapper.TaskMapper;
import org.solowev.taskmanager.project.repository.ProjectRepository;
import org.solowev.taskmanager.project.repository.TaskRepository;
import org.solowev.taskmanager.project.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskDto createTask(TaskRequestDto taskRequestDto) {
        if (taskRequestDto.getProjectId() == null) {
            throw new IllegalStateException("project id is null");
        }
        Project project = projectRepository.findById(taskRequestDto.getProjectId())
                .orElseThrow(() -> new TaskManagerException(ErrorCode.NOT_FOUND_EXCEPTION,
                        "couldn't find project by id"));
        Task task = taskMapper.toEntityFromRequest(taskRequestDto);
        task.setProject(project);
        task.setOwnerId(taskRequestDto.getOwnerId());
        task.setExecutorId(taskRequestDto.getExecutorId());
        task.setStatus(TaskStatusEnum.CREATED);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    @Transactional
    public TaskDto updateTask(TaskRequestDto taskDto) {
        Task task = getEntityById(taskDto.getId());
        taskMapper.updateTask(taskDto, task);
        taskRepository.save(task);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    @Transactional
    public TaskDto changeStatus(TaskRequestDto taskDto, TaskStatusEnum taskStatus) {
        Task task = getEntityById(taskDto.getId());
        taskMapper.updateTask(taskDto, task);
        task.setStatus(taskStatus);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public List<TaskDto> getByProject(Long projectId) {
        return taskMapper.toDto(taskRepository.findByProject_Id(projectId));
    }

    @Override
    @Transactional
    public List<TaskDto> getAllTasksByUser() {
        List<Task> tasks = taskRepository.findAllByExecutorId(SecurityUtils.getCurrentUser().getId());
        return taskMapper.toDto(tasks);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDto getById(Long taskId) {
        return taskMapper.toDto(getEntityById(taskId));
    }

    private Task getEntityById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskManagerException(ErrorCode.NOT_FOUND_EXCEPTION));
    }
}

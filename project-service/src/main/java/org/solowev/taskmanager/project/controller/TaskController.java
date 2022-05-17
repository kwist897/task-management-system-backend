package org.solowev.taskmanager.project.controller;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.project.dto.TaskDto;
import org.solowev.taskmanager.project.dto.TaskRequestDto;
import org.solowev.taskmanager.project.enums.TaskStatusEnum;
import org.solowev.taskmanager.project.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        return ResponseEntity.ok(taskService.createTask(taskRequestDto));
    }

    @PutMapping("/task")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskRequestDto taskRequestDto) {
        return ResponseEntity.ok(taskService.updateTask(taskRequestDto));
    }

    @PutMapping("/task/{status}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskRequestDto taskRequestDto,
                                              @PathVariable("status") TaskStatusEnum taskStatus) {
        return ResponseEntity.ok(taskService.changeStatus(taskRequestDto, taskStatus));
    }

    @GetMapping("/task/all")
    public ResponseEntity<List<TaskDto>> getAllByUser() {
        return ResponseEntity.ok(taskService.getAllTasksByUser());
    }

    @DeleteMapping("/task{taskId}")
    public ResponseEntity<Void> deleteById(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<TaskDto> getById(@PathVariable("taskId") Long taskId) {
        return ResponseEntity.ok(taskService.getById(taskId));
    }

    @GetMapping("/task/all/{projectId}")
    public ResponseEntity<List<TaskDto>> getByProjectId(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(taskService.getByProject(projectId));
    }
}

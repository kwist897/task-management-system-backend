package org.solowev.taskmanager.project.controller;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.project.dto.ProjectDto;
import org.solowev.taskmanager.project.service.ProjectService;
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
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/project")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        return ResponseEntity
                .ok(projectService.createProject(projectDto));
    }

    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable("projectId") Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/workspace/{workspaceId}/projects")
    public ResponseEntity<List<ProjectDto>> getProjects(@PathVariable("workspaceId") Long workspaceId) {
        return ResponseEntity.ok(projectService.findAllProjectByWorkspace(workspaceId));
    }

    @PutMapping("/project/all")
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectService.updateProject(projectDto));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(projectService.getById(projectId));
    }

}

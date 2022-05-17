package org.solowev.taskmanager.project.service;

import org.solowev.taskmanager.project.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto createProject(ProjectDto project);

    ProjectDto updateProject(ProjectDto project);

    void deleteProject(Long projectId);

    List<ProjectDto> findAllProjectByWorkspace(Long workspaceId);

    ProjectDto getById(Long projectId);
}

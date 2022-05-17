package org.solowev.taskmanager.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.base.exceptions.ErrorCode;
import org.solowev.taskmanager.base.exceptions.TaskManagerException;
import org.solowev.taskmanager.base.utils.SecurityUtils;
import org.solowev.taskmanager.project.domain.Project;
import org.solowev.taskmanager.project.dto.ProjectDto;
import org.solowev.taskmanager.project.mapper.ProjectMapper;
import org.solowev.taskmanager.project.repository.ProjectRepository;
import org.solowev.taskmanager.project.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        project.setOwnerId(SecurityUtils.getCurrentUser().getId());
        return projectMapper.toDto(projectRepository.save(project));
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        if (!projectRepository.existsById(projectDto.getId())) {
            throw new TaskManagerException(ErrorCode.NOT_FOUND_EXCEPTION);
        }
        Project project = findById(projectDto.getId());
        projectMapper.updateProject(projectDto, project);
        return projectMapper.toDto(projectRepository.save(project));
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public List<ProjectDto> findAllProjectByWorkspace(Long workspaceId) {
        return projectMapper.toDto(projectRepository.findAllByWorkspaceId(workspaceId));
    }

    @Override
    public ProjectDto getById(Long projectId) {
        return projectMapper.toDto(findById(projectId));
    }

    private Project findById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new TaskManagerException(ErrorCode.NOT_FOUND_EXCEPTION));
    }
}

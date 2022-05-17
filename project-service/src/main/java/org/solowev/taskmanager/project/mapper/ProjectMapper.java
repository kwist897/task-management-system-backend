package org.solowev.taskmanager.project.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.solowev.taskmanager.base.BaseMapper;
import org.solowev.taskmanager.project.domain.Project;
import org.solowev.taskmanager.project.dto.ProjectDto;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends BaseMapper<Project, ProjectDto> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProject(ProjectDto requestDto, @MappingTarget Project project);
}

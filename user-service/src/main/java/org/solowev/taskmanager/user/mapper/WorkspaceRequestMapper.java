package org.solowev.taskmanager.user.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.solowev.taskmanager.base.BaseMapper;
import org.solowev.taskmanager.user.domain.Workspace;
import org.solowev.taskmanager.user.dto.request.WorkspaceRequestDto;

@Mapper(componentModel = "spring")
public interface WorkspaceRequestMapper extends BaseMapper<Workspace, WorkspaceRequestDto> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "group.id", ignore = true)
    void updateEntityByRequestDto(@MappingTarget Workspace workspace, WorkspaceRequestDto workspaceRequestDto);
}

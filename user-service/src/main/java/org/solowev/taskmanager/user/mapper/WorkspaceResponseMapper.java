package org.solowev.taskmanager.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.solowev.taskmanager.base.BaseMapper;
import org.solowev.taskmanager.user.domain.Workspace;
import org.solowev.taskmanager.user.dto.response.WorkspaceResponseDto;

@Mapper(componentModel = "spring")
public interface WorkspaceResponseMapper extends BaseMapper<Workspace, WorkspaceResponseDto> {
    @Override
    @Mapping(target = "group.participants", ignore = true)
    WorkspaceResponseDto toDto(Workspace entity);
}

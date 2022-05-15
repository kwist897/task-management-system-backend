package org.solowev.taskmanager.user.mapper;

import org.mapstruct.Mapper;
import org.solowev.taskmanager.base.BaseMapper;
import org.solowev.taskmanager.user.domain.Group;
import org.solowev.taskmanager.user.dto.response.GroupResponseDto;

@Mapper(componentModel = "spring")
public interface GroupResponseMapper extends BaseMapper<Group, GroupResponseDto> {
}

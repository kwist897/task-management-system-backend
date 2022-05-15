package org.solowev.taskmanager.user.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.solowev.taskmanager.base.BaseMapper;
import org.solowev.taskmanager.user.domain.Group;
import org.solowev.taskmanager.user.dto.request.GroupRequestDto;

@Mapper(componentModel = "spring")
public interface GroupRequestMapper extends BaseMapper<Group, GroupRequestDto> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityByRequestDto(@MappingTarget Group group, GroupRequestDto groupRequestDto);
}

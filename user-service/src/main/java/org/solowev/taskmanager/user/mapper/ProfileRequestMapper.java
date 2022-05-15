package org.solowev.taskmanager.user.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.solowev.taskmanager.base.BaseMapper;
import org.solowev.taskmanager.user.domain.Profile;
import org.solowev.taskmanager.user.dto.request.ProfileRequestDto;

@Mapper(componentModel = "spring")
public interface ProfileRequestMapper extends BaseMapper<Profile, ProfileRequestDto> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityByRequestDto(@MappingTarget Profile profile, ProfileRequestDto profileRequestDto);
}

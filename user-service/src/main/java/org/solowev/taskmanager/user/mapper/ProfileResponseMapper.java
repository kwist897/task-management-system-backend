package org.solowev.taskmanager.user.mapper;

import org.mapstruct.Mapper;
import org.solowev.taskmanager.base.BaseMapper;
import org.solowev.taskmanager.user.domain.Profile;
import org.solowev.taskmanager.user.dto.response.ProfileResponseDto;

@Mapper(componentModel = "spring")
public interface ProfileResponseMapper extends BaseMapper<Profile, ProfileResponseDto> {
}

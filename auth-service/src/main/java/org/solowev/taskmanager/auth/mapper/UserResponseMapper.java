package org.solowev.taskmanager.auth.mapper;

import org.mapstruct.Mapper;
import org.solowev.taskmanager.auth.domain.User;
import org.solowev.taskmanager.auth.dto.response.UserResponseDto;
import org.solowev.taskmanager.base.BaseMapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper extends BaseMapper<User, UserResponseDto> {
}

package org.solowev.taskmanager.auth.mapper;

import org.mapstruct.Mapper;
import org.solowev.taskmanager.auth.domain.User;
import org.solowev.taskmanager.auth.dto.request.UserRequestDto;

@Mapper(componentModel = "spring")
public interface UserRequestMapper extends BaseMapper<User, UserRequestDto> {

}

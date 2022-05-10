package org.solowev.taskmanager.auth.mapper;

import org.mapstruct.Mapper;
import org.solowev.taskmanager.auth.domain.AccessToken;
import org.solowev.taskmanager.auth.dto.response.AccessTokenResponseDto;

@Mapper(componentModel = "spring")
public interface AccessTokenMapper extends BaseMapper<AccessToken, AccessTokenResponseDto>{
}

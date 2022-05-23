package org.solowev.taskmanager.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.solowev.taskmanager.auth.domain.RefreshToken;
import org.solowev.taskmanager.auth.dto.response.RefreshTokenResponseDto;
import org.solowev.taskmanager.base.BaseMapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper extends BaseMapper<RefreshToken, RefreshTokenResponseDto> {
    @Override
    @Mapping(target="token", source="refreshToken")
    RefreshToken toEntity(RefreshTokenResponseDto dto);
}

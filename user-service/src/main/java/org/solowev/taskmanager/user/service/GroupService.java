package org.solowev.taskmanager.user.service;

import org.solowev.taskmanager.user.dto.request.GroupRequestDto;
import org.solowev.taskmanager.user.dto.response.GroupResponseDto;

import java.util.List;

public interface GroupService {
    GroupResponseDto createGroup(GroupRequestDto groupRequestDto);
    List<GroupResponseDto> getUserGroups(Long profileId);
    GroupResponseDto updateGroup(GroupRequestDto groupRequestDto);
    GroupResponseDto addParticipant(Long profileId);
}

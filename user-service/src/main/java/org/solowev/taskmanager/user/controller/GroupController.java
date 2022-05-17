package org.solowev.taskmanager.user.controller;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.base.aspect.WebCallJoinPoint;
import org.solowev.taskmanager.user.dto.request.GroupRequestDto;
import org.solowev.taskmanager.user.dto.response.GroupResponseDto;
import org.solowev.taskmanager.user.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @WebCallJoinPoint
    @PostMapping("/group")
    public ResponseEntity<GroupResponseDto> createGroup(@RequestBody GroupRequestDto groupRequestDto) {
        return ResponseEntity.ok(groupService.createGroup(groupRequestDto));
    }

    @WebCallJoinPoint
    @GetMapping("/profile/{profileId}/group")
    public ResponseEntity<List<GroupResponseDto>> getGroups(@PathVariable("profileId") Long profileId) {
        return ResponseEntity.ok(groupService.getUserGroups(profileId));
    }

    @WebCallJoinPoint
    @PutMapping("/group/{groupId}")
    public ResponseEntity<GroupResponseDto> updateGroup(@RequestBody GroupRequestDto groupRequestDto,
                                                        @PathVariable("groupId") Long groupId) {
        return ResponseEntity.ok(groupService.updateGroup(groupRequestDto, groupId));
    }

    @WebCallJoinPoint
    @PostMapping("/group/{groupId}/profile/{profileId}")
    public ResponseEntity<GroupResponseDto> addParticipant(@PathVariable("groupId") Long groupId, @PathVariable("profileId") Long profileId) {
        return ResponseEntity.ok(groupService.addParticipant(profileId, groupId));
    }

    @WebCallJoinPoint
    @DeleteMapping("/group/{groupId}/profile/{profileId}")
    public ResponseEntity<GroupResponseDto> removeParticipant(@PathVariable("groupId") Long groupId, @PathVariable("profileId") Long profileId) {
        return ResponseEntity.ok(groupService.deleteParticipant(profileId, groupId));
    }

}

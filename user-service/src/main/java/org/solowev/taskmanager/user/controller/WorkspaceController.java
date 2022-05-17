package org.solowev.taskmanager.user.controller;

import lombok.RequiredArgsConstructor;
import org.solowev.taskmanager.base.aspect.WebCallJoinPoint;
import org.solowev.taskmanager.user.dto.request.WorkspaceRequestDto;
import org.solowev.taskmanager.user.dto.response.UserWorkspacesResponseDto;
import org.solowev.taskmanager.user.dto.response.WorkspaceResponseDto;
import org.solowev.taskmanager.user.service.WorkspaceService;
import org.springframework.http.ResponseEntity;
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
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @WebCallJoinPoint
    @PostMapping("/workspace")
    public ResponseEntity<WorkspaceResponseDto> createWorkspace(@RequestBody WorkspaceRequestDto workspaceRequestDto) {
        return ResponseEntity.ok(workspaceService.createWorkspace(workspaceRequestDto));
    }

    @WebCallJoinPoint
    @GetMapping("/workspace")
    public ResponseEntity<UserWorkspacesResponseDto> getUserWorkspaces() {
        return ResponseEntity.ok(workspaceService.getAllWorkspaces());
    }

    @WebCallJoinPoint
    @GetMapping("/group/{groupId}/workspace")
    public ResponseEntity<List<WorkspaceResponseDto>> getAllByGroupId(@PathVariable("groupId") Long groupId) {
        return ResponseEntity.ok(workspaceService.getWorkspacesByGroupId(groupId));
    }

    @WebCallJoinPoint
    @PutMapping("/workspace/{workspaceId}")
    public ResponseEntity<WorkspaceResponseDto> workspaceResponseDto(@RequestBody WorkspaceRequestDto workspaceRequestDto,
                                                                     @PathVariable("workspaceId") Long workspaceId) {
        return ResponseEntity.ok(workspaceService.updateWorkspace(workspaceRequestDto, workspaceId));
    }
}

package web_cybertron.taskmanagementsystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web_cybertron.taskmanagementsystem.entity.Users;
import web_cybertron.taskmanagementsystem.payload.ApiResponse;
import web_cybertron.taskmanagementsystem.payload.MemberDTO;
import web_cybertron.taskmanagementsystem.payload.WorkspaceDTO;
import web_cybertron.taskmanagementsystem.security.CurrentUser;
import web_cybertron.taskmanagementsystem.service.WorkspaceService;

import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {

    @Autowired
    WorkspaceService workspaceService;

    @PostMapping
    public HttpEntity<?> addWorkspace(@Valid @RequestBody WorkspaceDTO workspaceDto, @CurrentUser Users user) {
        ApiResponse apiResponse = workspaceService.addWorkspace(workspaceDto, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editWorkspace(@PathVariable Long id, @RequestBody WorkspaceDTO workspaceDto) {
        ApiResponse apiResponse = workspaceService.editWorkspace(id, workspaceDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/changeOwner/{id}")
    public HttpEntity<?> changeOwnerOfWorkspace(@PathVariable Long id, @RequestParam UUID ownerId) {
        ApiResponse apiResponse = workspaceService.changeOwnerOfWorkspace(id, ownerId);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWorkspace(@PathVariable Long id) {
        ApiResponse apiResponse = workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PostMapping("/addOrEditOrRemoveMember/{id}")
    public HttpEntity<?> addOrEditOrRemoveMemberToWorkspace(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {
        ApiResponse apiResponse = workspaceService.addOrEditOrRemoveMemberToWorkspace(id, memberDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/join")
    public HttpEntity<?> joinToWorkspace(@RequestParam Long workspaceId, @CurrentUser Users user) {
        ApiResponse apiResponse = workspaceService.joinToWorkspace(workspaceId, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}

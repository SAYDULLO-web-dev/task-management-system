package web_cybertron.taskmanagementsystem.service;

import web_cybertron.taskmanagementsystem.entity.Users;
import web_cybertron.taskmanagementsystem.payload.ApiResponse;
import web_cybertron.taskmanagementsystem.payload.WorkspaceDto;

import java.util.UUID;

public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDto workspaceDto, Users user);

    ApiResponse editWorkspace(Long id, WorkspaceDto workspaceDto);

    ApiResponse changeOwnerOfWorkspace(Long id, UUID ownerId);

    ApiResponse deleteWorkspace(Long id);
}

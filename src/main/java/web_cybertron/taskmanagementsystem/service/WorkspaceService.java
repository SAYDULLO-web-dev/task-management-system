package web_cybertron.taskmanagementsystem.service;

import web_cybertron.taskmanagementsystem.entity.Users;
import web_cybertron.taskmanagementsystem.payload.ApiResponse;
import web_cybertron.taskmanagementsystem.payload.MemberDTO;
import web_cybertron.taskmanagementsystem.payload.WorkspaceDTO;

import java.util.UUID;

public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDto, Users user);

    ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDto);

    ApiResponse changeOwnerOfWorkspace(Long id, UUID ownerId);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveMemberToWorkspace(Long id, MemberDTO memberDTO);
}

package web_cybertron.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import web_cybertron.taskmanagementsystem.entity.*;
import web_cybertron.taskmanagementsystem.entity.enums.MemberActionType;
import web_cybertron.taskmanagementsystem.entity.enums.WorkspacePermissionName;
import web_cybertron.taskmanagementsystem.entity.enums.WorkspaceRoleName;
import web_cybertron.taskmanagementsystem.payload.ApiResponse;
import web_cybertron.taskmanagementsystem.payload.MemberDTO;
import web_cybertron.taskmanagementsystem.payload.WorkspaceDTO;
import web_cybertron.taskmanagementsystem.repository.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    WorkspaceUserRepository workspaceUserRepository;

    @Autowired
    WorkspaceRoleRepository workspaceRoleRepository;

    @Autowired
    WorkspacePermissionRepository workspacePermissionRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Adds a new workspace with the specified details and assigns roles and permissions to the workspace.
     *
     * @param workspaceDto the data transfer object containing workspace details
     * @param user the user who is creating the workspace
     * @return ApiResponse indicating the success or failure of the workspace creation operation
     */
    @Override
    public ApiResponse addWorkspace(WorkspaceDTO workspaceDto, Users user) {

        // Check if workspace already exists
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDto.getName())) {
            return new ApiResponse("This workspace already exists", false);
        }

        // Create new workspace
        Workspace workspace = new Workspace(
                workspaceDto.getName(),
                workspaceDto.getColor(),
                user,
                workspaceDto.getAvatarId() == null ? null : attachmentRepository.findById(workspaceDto.getAvatarId())
                        .orElseThrow(() -> new ResourceNotFoundException("Attachment (Icon) not found"))
        );
        workspaceRepository.save(workspace);

        // Add owner role to workspace
        WorkspaceRole ownerRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_OWNER.name(),
                null
        ));

        // Add admin role to workspace
        WorkspaceRole adminRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_ADMIN.name(),
                null
        ));

        // Add member role to workspace
        WorkspaceRole memberRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_MEMBER.name(),
                null
        ));

        // Add guest role to workspace
        WorkspaceRole guestRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_GUEST.name(),
                null
        ));

        // Add workspace owner, admin, member, guest role's permissions
        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissionsList = new ArrayList<>();
        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames) {
            // owner role has all permissions
            workspacePermissionsList.add(new WorkspacePermission(
                    ownerRole,
                    workspacePermissionName
            ));
            // admin role has all permissions except owner permissions
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissionsList.add(new WorkspacePermission(
                        adminRole,
                        workspacePermissionName
                ));
            }
            // member role has only member permissions
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                workspacePermissionsList.add(new WorkspacePermission(
                        memberRole,
                        workspacePermissionName
                ));
            }
            // guest role has only guest permissions
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissionsList.add(new WorkspacePermission(
                        guestRole,
                        workspacePermissionName
                ));
            }
        }
        workspacePermissionRepository.saveAll(workspacePermissionsList);

        // Add user to workspace
        workspaceUserRepository.save(new WorkspaceUser(
                workspace,
                user,
                ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        ));
        return new ApiResponse("Workspace added", true);
    }

    //TODO: write method body
    @Override
    public ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDto) {
        return null;
    }

    //TODO: write method body
    @Override
    public ApiResponse changeOwnerOfWorkspace(Long id, UUID ownerId) {
        return null;
    }

    /**
     * Deletes a workspace by its ID.
     *
     * @param id the ID of the workspace to be deleted
     * @return ApiResponse indicating the success or failure of the deletion operation
     */
    @Override
    public ApiResponse deleteWorkspace(Long id) {
        try {
            workspaceRepository.deleteById(id);
            return new ApiResponse("Workspace deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error occurred while deleting workspace", false);
        }
    }

    /**
     * Adds, edits, or removes a member (or guest) to/from a workspace.
     *
     * @param id the ID of the workspace
     * @param memberDTO the data transfer object containing member details and action type
     * @return ApiResponse indicating the success or failure of the operation
     */
    @Override
    public ApiResponse addOrEditOrRemoveMemberToWorkspace(Long id, MemberDTO memberDTO) {
        if (memberDTO.getMemberActionType().equals(MemberActionType.ADD)) {
            // Check if member is already a member of this workspace
            Optional<WorkspaceUser> byWorkspaceIdAndUserId = workspaceUserRepository.findByWorkspaceIdAndUserId(id, memberDTO.getId());
            if (byWorkspaceIdAndUserId.isEmpty()) {
                return new ApiResponse("This member (or guest) is already joined to the workspace.", false);
            }
            // Invite member to workspace by owner or admin
            WorkspaceUser workspaceUser = new WorkspaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Workspace not found")),
                    userRepository.findById(memberDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Joining member or guest not found")),
                    workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Joining member or guest role not found")),
                    new Timestamp(System.currentTimeMillis()), // inviting date
                    null  // joining date is null, because member (or guest) is not joined yet
            );
            workspaceUserRepository.save(workspaceUser);
        } else if (memberDTO.getMemberActionType().equals(MemberActionType.EDIT)) {
            // Edit member or guest's role in workspace
            WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, memberDTO.getId()).orElseGet(WorkspaceUser::new);
            workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Member or guest's editing role not found")));
            workspaceUserRepository.save(workspaceUser);
        } else if (memberDTO.getMemberActionType().equals(MemberActionType.REMOVE)) {
            // Remove member or guest from workspace
            workspaceUserRepository.deleteByWorkspaceIdAndUserId(id, memberDTO.getId());
        } else {
            return new ApiResponse("Member action type is not valid", false);
        }
        return new ApiResponse("Action (inviting/editing/removing member or guest) finished successfully.", true);
    }
}

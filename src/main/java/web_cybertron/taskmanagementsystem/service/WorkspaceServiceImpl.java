package web_cybertron.taskmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import web_cybertron.taskmanagementsystem.entity.Users;
import web_cybertron.taskmanagementsystem.entity.Workspace;
import web_cybertron.taskmanagementsystem.payload.ApiResponse;
import web_cybertron.taskmanagementsystem.payload.WorkspaceDto;
import web_cybertron.taskmanagementsystem.repository.AttachmentRepository;
import web_cybertron.taskmanagementsystem.repository.WorkspaceRepository;

import java.util.UUID;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public ApiResponse addWorkspace(WorkspaceDto workspaceDto, Users user) {
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDto.getName())) {
            return new ApiResponse("This workspace already exists", false);
        }
        Workspace workspace = new Workspace(
                workspaceDto.getName(),
                workspaceDto.getColor(),
                user,
                workspaceDto.getAvatarId()==null?null:attachmentRepository.findById(workspaceDto.getAvatarId())
                        .orElseThrow(() -> new ResourceNotFoundException("Attachment (Icon) not found"))
        );
        workspaceRepository.save(workspace);
        return new ApiResponse("Workspace added", true);
    }

    @Override
    public ApiResponse editWorkspace(Long id, WorkspaceDto workspaceDto) {
        return null;
    }

    @Override
    public ApiResponse changeOwnerOfWorkspace(Long id, UUID ownerId) {
        return null;
    }

    @Override
    public ApiResponse deleteWorkspace(Long id) {
        return null;
    }
}

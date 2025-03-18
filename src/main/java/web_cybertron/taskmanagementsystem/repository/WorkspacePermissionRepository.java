package web_cybertron.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web_cybertron.taskmanagementsystem.entity.WorkspacePermission;

import java.util.UUID;

/*
 *@project: task-management-system
 *@created on: 3/19/2025
 *@author: SAYDULLO-web-dev
 */
@Repository
public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
}

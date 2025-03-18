package web_cybertron.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web_cybertron.taskmanagementsystem.entity.WorkspaceRole;

import java.util.UUID;
@Repository
public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {
}

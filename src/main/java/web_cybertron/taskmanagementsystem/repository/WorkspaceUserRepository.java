package web_cybertron.taskmanagementsystem.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import web_cybertron.taskmanagementsystem.entity.WorkspaceUser;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, UUID> {

    Optional<WorkspaceUser> findByWorkspaceIdAndUserId(Long workspaceId, UUID memberOrGuestId);

    @Transactional
    @Modifying
    void deleteByWorkspaceIdAndUserId(Long workspaceId, UUID userId);
}

package web_cybertron.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web_cybertron.taskmanagementsystem.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}

package web_cybertron.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web_cybertron.taskmanagementsystem.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);
}

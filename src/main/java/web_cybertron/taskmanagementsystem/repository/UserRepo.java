package web_cybertron.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web_cybertron.taskmanagementsystem.entity.Users;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<Users, UUID> {

    boolean existsByEmail(String email);

    Optional<Users>  findByEmail(String email);

}

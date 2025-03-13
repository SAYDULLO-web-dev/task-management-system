package web_cybertron.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web_cybertron.taskmanagementsystem.entity.Users;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<Users, UUID> {

    boolean existsByEmail(String email);

    Optional<Users>  findByEmail(String email);
}

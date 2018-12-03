package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollubmy.server.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
}

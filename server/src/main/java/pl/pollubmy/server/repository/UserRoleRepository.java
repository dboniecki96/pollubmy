package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollubmy.server.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
}

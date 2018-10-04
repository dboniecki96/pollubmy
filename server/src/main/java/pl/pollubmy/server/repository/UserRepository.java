package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollubmy.server.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findEmailPollub(String emailPollub);
}

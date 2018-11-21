package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollubmy.server.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailPollub(String emailPollub);
    Optional<User> findByLogin(String login);
    Optional<User> findByEmailPollubOrLogin(String emailPollub, String login);
    Optional<User> findByLoginAndIsActive(String login, boolean isActive);
}

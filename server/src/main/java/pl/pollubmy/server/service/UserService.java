package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.exceptions.UserNotFoundException;
import pl.pollubmy.server.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserInformation(final String login) {

        Optional<User> user = userRepository.findByLoginAndIsActive(login, true);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User with this login not found");
        }
    }

    public List<User> getAllUsers() {

        List<User> usersList = userRepository.findAll().stream().filter(user -> user.isActive() == true).collect(Collectors.toList());

        if (usersList.isEmpty()) {
            throw new UserNotFoundException("No user in database");
        } else {
            return usersList;
        }
    }

    public void closeAccount() {
    }
}

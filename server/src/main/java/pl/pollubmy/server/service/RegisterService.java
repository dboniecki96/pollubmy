package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.exceptions.UserFoundException;
import pl.pollubmy.server.repository.UserRepository;

import java.util.Optional;

@Service
public class RegisterService {

    private final UserRepository userRepository;

    @Autowired
    public RegisterService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(final User user) {

        Optional ifUserWithEmailExist = this.userRepository.findByEmailPollub(user.getEmailPollub());
        Optional ifUserWithLoginExist = this.userRepository.findByLogin(user.getLogin());

        if (ifUserWithEmailExist.isPresent()) {
            throw new UserFoundException("User with this email exist.");
        } else if (ifUserWithLoginExist.isPresent()) {
            throw new UserFoundException("User with this login exist.");
        } else {
            return this.userRepository.save(user);
        }
    }
}

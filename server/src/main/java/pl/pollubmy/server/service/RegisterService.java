package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.entity.UserRole;
import pl.pollubmy.server.enumType.RoleType;
import pl.pollubmy.server.exceptions.UserFoundException;
import pl.pollubmy.server.repository.UserRepository;
import pl.pollubmy.server.repository.UserRoleRepository;

import java.util.Optional;

@Service
public class RegisterService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRoleRepository userRoleRepository, final UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(final User user) {

        Optional ifUserWithEmailExist = this.userRepository.findByEmailPollub(user.getEmailPollub());
        Optional ifUserWithLoginExist = this.userRepository.findByLogin(user.getLogin());

        if (ifUserWithEmailExist.isPresent()) {
            throw new UserFoundException("User with this email exist.");
        } else if (ifUserWithLoginExist.isPresent()) {
            throw new UserFoundException("User with this login exist.");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserRole(new UserRole(user, RoleType.STUDENT));
            return this.userRepository.save(user);
        }
    }
}

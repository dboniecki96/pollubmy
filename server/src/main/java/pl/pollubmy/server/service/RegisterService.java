package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.entity.UserRole;
import pl.pollubmy.server.enumType.RoleType;
import pl.pollubmy.server.exceptions.UserFoundException;
import pl.pollubmy.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(final UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(final User user) {

        Optional ifEmailExist = this.userRepository.findByEmailPollub(user.getEmailPollub());
        Optional ifLoginExist = this.userRepository.findByLogin(user.getLogin());

        if (ifEmailExist.isPresent()) {
            throw new UserFoundException("User with this email exist.");
        } else if (ifLoginExist.isPresent()) {
            throw new UserFoundException("User with this login exist.");
        } else {

            List userPrivileges = new ArrayList();

            userPrivileges.add(new UserRole(user, RoleType.STUDENT));

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserRole(userPrivileges);

            return this.userRepository.save(user);
        }
    }
}

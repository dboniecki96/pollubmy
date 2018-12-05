package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.entity.dto.UserDTO;
import pl.pollubmy.server.entity.dto.UserDTOConverter;
import pl.pollubmy.server.entity.tool.CopyPropertiesTool;
import pl.pollubmy.server.exceptions.UserFoundException;
import pl.pollubmy.server.exceptions.UserNotFoundException;
import pl.pollubmy.server.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO getUserInformation(final String login) {

        Optional<User> user = userRepository.findByLoginAndIsActive(login, true);

        if (user.isPresent()) {
            return UserDTOConverter.toDTO(user.get());
        } else {
            throw new UserNotFoundException("User with this login not found");
        }
    }

    public List<UserDTO> getAllUsers() {

        List<User> usersList = userRepository.findAll().stream().filter(user -> user.isActive()).collect(Collectors.toList());

        if (usersList.isEmpty()) {
            throw new UserNotFoundException("User with this login not found");
        } else {
            return usersList.stream().map(UserDTOConverter::toDTO).collect(Collectors.toList());
        }
    }

    public void closeAccount(String login) {

        Optional<User> userToDeactivate = this.userRepository.findByLogin(login);

        if (userToDeactivate.isPresent()) {
            userToDeactivate.get().setActive(false);
            this.userRepository.save(userToDeactivate.get());
        } else {
            throw new UserNotFoundException("User with this login not found");
        }
    }

    public void changePassword(String newPassword, String login) {

        Optional<User> userChangePassword = this.userRepository.findByLogin(login);

        if (userChangePassword.isPresent()) {

            SecurityContextHolder.clearContext();

            userChangePassword.get().setPassword(passwordEncoder.encode(newPassword));

            this.userRepository.save(userChangePassword.get());
        } else {
            throw new UserNotFoundException("User with this login not found");
        }
    }

    public UserDTO updateUser(UserDTO userDTO, String login) {

        boolean ifUserWithExistEmail = this.userRepository.findByEmailPollub(userDTO.getEmailPollub()).isPresent();
        boolean ifUserWithExistLogin = this.userRepository.findByLogin(userDTO.getLogin()).isPresent();

        if (ifUserWithExistEmail || ifUserWithExistLogin)
            throw new UserFoundException("User with this email or login exist");

        Optional<User> userToUpdate = this.userRepository.findByLogin(login);

        if (userToUpdate.isPresent()) {
            User user = userToUpdate.get();

            if ((userDTO.getUserAddress() != null) && (userDTO.getUserDetails() != null)) {
                CopyPropertiesTool.copyNonNullProperties(userDTO.getUserDetails(), user.getUserDetails());
                CopyPropertiesTool.copyNonNullProperties(userDTO.getUserAddress(), user.getUserAddress());
            }

            userDTO.setUserAddress(null);
            userDTO.setUserDetails(null);
            CopyPropertiesTool.copyNonNullProperties(userDTO, user);
            this.userRepository.save(user);

            return UserDTOConverter.toDTO(user);
        } else {
            throw new UserNotFoundException("User with this login not found");
        }
    }
}

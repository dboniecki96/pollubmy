package pl.pollubmy.server.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.entity.UserDetails;
import pl.pollubmy.server.entity.dto.UserDTO;
import pl.pollubmy.server.entity.dto.UserDTOConverter;
import pl.pollubmy.server.entity.tool.CopyPropertiesTool;
import pl.pollubmy.server.exceptions.UserNotFoundException;
import pl.pollubmy.server.exceptions.WrongRequestException;
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
            throw new UserNotFoundException("No user in database");
        } else {
            return usersList.stream().map(UserDTOConverter::toDTO).collect(Collectors.toList());
        }
    }

    public void closeAccount(String login) {

        Optional<User> userToDeactivate = userRepository.findByLogin(login);

        if (userToDeactivate.isPresent()) {
            userToDeactivate.get().setActive(false);
            userRepository.save(userToDeactivate.get());
        } else {
            throw new UserNotFoundException("User with this login not found");
        }
    }

    public void changePassword(String password, String login) {

        Optional<User> userChangePassword = userRepository.findByLogin(login);

        if (userChangePassword.isPresent()) {
            userChangePassword.get().setPassword(passwordEncoder.encode(password));
        } else {
            throw new UserNotFoundException("User with this login not found");
        }
    }

    public UserDTO updateUser(UserDTO userDTO, String login) {

        if(userDTO.getUserDetails()==null || userDTO.getUserAddress()== null){
            throw new WrongRequestException("Empty field userDetailsId or userAddressId");
        }

        Optional<User> userToUpdate = this.userRepository.findByLogin(login);

        if (userToUpdate.isPresent()) { User user = userToUpdate.get();

            CopyPropertiesTool.copyNonNullProperties(userDTO, user);

            this.userRepository.save(user);

            return UserDTOConverter.toDTO(user);
        } else {
            throw new UserNotFoundException("User with this login not found");
        }

    }
}

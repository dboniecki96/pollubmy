package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.exceptions.UserNotFoundException;
import pl.pollubmy.server.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(final User user) {
        Optional isEmailPollub = this.userRepository.findEmailPollub(user.getEmailPollub()).stream().findFirst();

        if(isEmailPollub.isPresent()){
            throw new UserNotFoundException("User with this email exist!");
        }else{
            return this.userRepository.save(user);
        }
    }
}

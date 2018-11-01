package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.exceptions.UserNotFoundException;
import pl.pollubmy.server.repository.UserRepository;
import pl.pollubmy.server.security.JwtUserPrincipal;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginOrEmail) throws UsernameNotFoundException {

        Optional<User> userWithEmailOrLoginExist = userRepository.findByEmailPollubOrLogin(loginOrEmail, loginOrEmail);

        if (userWithEmailOrLoginExist.isPresent()) {
            return JwtUserPrincipal.create(userWithEmailOrLoginExist.get());
        } else {
            throw new UserNotFoundException("User with this email or login doesn't exist");
        }
    }

    @Transactional
    public UserDetails loadUserById(String userId){
        Optional<User> userWithUsedIdExist = userRepository.findByUserId(userId);

        if (userWithUsedIdExist.isPresent()) {
            return JwtUserPrincipal.create(userWithUsedIdExist.get());
        } else {
            throw new UserNotFoundException("User with this email or login doesn't exist");
        }
    }
}

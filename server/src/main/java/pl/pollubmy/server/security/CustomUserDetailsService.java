package pl.pollubmy.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.repository.UserRepository;

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
    public UserDetails loadUserByUsername(final String loginOrEmail) {

        User foundUser = loadFromDBUserByLoginOrEmail(loginOrEmail);
        return new org.springframework.security.core.userdetails.User(foundUser.getLogin(), foundUser.getPassword(), AuthorityUtils.createAuthorityList(foundUser.getUserRole().toString()));
    }

    public User loadFromDBUserByLoginOrEmail(final String loginOrEmail) {
        Optional<User> userWithEmailOrLoginExist = this.userRepository.findByEmailPollubOrLogin(loginOrEmail, loginOrEmail);

        if (userWithEmailOrLoginExist.isPresent()) {
            return userWithEmailOrLoginExist.get();
        } else {
            throw new UsernameNotFoundException("User with this email or login doesn't exist");
        }
    }
}

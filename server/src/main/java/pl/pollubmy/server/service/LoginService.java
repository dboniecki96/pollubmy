package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.UserToLogin;

@Service
public class LoginService {

    private AuthenticationManager authenticationManager;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String tryLogin(UserToLogin userToLogin) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userToLogin.getLoginOrEmail(), userToLogin.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "OK";
    }
}

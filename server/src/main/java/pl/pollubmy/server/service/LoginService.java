package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.UserToLogin;

@Service
public class LoginService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public String tryLogin(UserToLogin userToLogin) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userToLogin.getLoginOrEmail(),
                        userToLogin.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity <>(new JwtAuthenticationResponse(jwt), HttpStatus.OK);

       /* Optional<User> ifUserWithLoginOrEmailExist = userRepository.findByEmailPollubOrLogin(userToLogin.getLoginOrEmail(), userToLogin.getLoginOrEmail());

        if(ifUserWithLoginOrEmailExist.isPresent()){

            User foundUser = ifUserWithLoginOrEmailExist.get();
            String passwordDecoded = passwordEncoder.encode(userToLogin.getPassword());

            if(passwordDecoded.equals(foundUser.getPassword())){
                return jwtGenerator.generate(foundUser);
            }
            else{
                throw new RuntimeException("HashPass" + passwordDecoded);
            }
        }else{
            throw new UserNotFoundException("User with this email or login doesn't exist");
        }*/
    }
}

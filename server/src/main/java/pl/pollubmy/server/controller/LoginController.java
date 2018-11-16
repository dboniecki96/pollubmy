package pl.pollubmy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollubmy.server.entity.UserToLogin;
import pl.pollubmy.server.service.LoginService;

@RestController
@RequestMapping("/logged")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/access")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> authenticateUser(@RequestBody final UserToLogin userToLogin) {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}

package pl.pollubmy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.service.RegisterService;

@RestController
public class RegisterController {

    private final RegisterService userService;

    @Autowired
    public RegisterController(final RegisterService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> addNewUser(@RequestBody final User user) {
        return new ResponseEntity<>(this.userService.createUser(user), HttpStatus.OK);
    }
}

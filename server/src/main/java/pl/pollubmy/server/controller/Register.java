package pl.pollubmy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.service.UserService;

@RestController
public class Register {

    private UserService userService;

    @Autowired
    public Register(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody final User user) {
        return new ResponseEntity<>(this.userService.createUser(user), HttpStatus.OK);
    }

}

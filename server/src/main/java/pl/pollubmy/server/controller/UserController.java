package pl.pollubmy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.entity.UserLogged;
import pl.pollubmy.server.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getUser(final Principal principal) {
        return new ResponseEntity<>(this.userService.getUserInformation(UserLogged.getLogin(principal)), HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @PatchMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deactivateUser() {
        this.userService.closeAccount();
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> editUser(@RequestBody final User user) {
        return new ResponseEntity<>(this.userService, HttpStatus.OK);
    }
}

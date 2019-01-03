package pl.pollubmy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pollubmy.server.entity.UserLogged;
import pl.pollubmy.server.entity.dto.PasswordDTO;
import pl.pollubmy.server.entity.dto.UserDTO;
import pl.pollubmy.server.service.UserService;

import java.security.Principal;

@RestController
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
    public ResponseEntity<?> deactivateUser(final Principal principal) {
        this.userService.closeAccount(UserLogged.getLogin(principal));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> editUser(@RequestBody final UserDTO userDTO, Principal principal) {
        return new ResponseEntity<>(this.userService.updateUser(userDTO, UserLogged.getLogin(principal)), HttpStatus.OK);
    }

    @PatchMapping("/updatePassword")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> changePassword(@RequestBody final PasswordDTO userPassword, Principal principal) {
        this.userService.changePassword(
                userPassword.getNewPassword(),
                UserLogged.getLogin(principal));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

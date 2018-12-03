package pl.pollubmy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollubmy.server.entity.PrivateLesson;
import pl.pollubmy.server.entity.UserLogged;
import pl.pollubmy.server.service.PrivateLessonService;

import java.security.Principal;

@RestController
@RequestMapping("/lesson")
public class PrivateLessonController {

    private final PrivateLessonService privateLessonService;

    @Autowired
    public PrivateLessonController(PrivateLessonService privateLessonService) {
        this.privateLessonService = privateLessonService;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getStudyInformation() {
        return new ResponseEntity<>(this.privateLessonService.getStudyInformation(), HttpStatus.OK);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> createPrivateLesson(final @RequestBody PrivateLesson privateLesson,final Principal principal) {
        return new ResponseEntity<>(this.privateLessonService.newPrivateLesson(
                privateLesson,
                UserLogged.getLogin(principal)),
                HttpStatus.OK);
    }
}

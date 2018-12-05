package pl.pollubmy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollubmy.server.entity.PrivateLesson;
import pl.pollubmy.server.entity.UserLogged;
import pl.pollubmy.server.entity.dto.PrivateLessonDTO;
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
    public ResponseEntity<?> createPrivateLesson(@RequestBody final PrivateLesson privateLesson, final Principal principal) {
        this.privateLessonService.newLesson(privateLesson, UserLogged.getLogin(principal));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAllActivePrivateLesson() {
        return new ResponseEntity<>(this.privateLessonService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/my")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getMyLesson(final Principal principal) {
        return new ResponseEntity<>(this.privateLessonService.getMy(UserLogged.getLogin(principal)), HttpStatus.OK);
    }

    @PatchMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteLesson(final Principal principal, @RequestParam("lessonId") final String lessonId) {
        this.privateLessonService.deactivateLesson(UserLogged.getLogin(principal), lessonId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> editPost(@RequestBody final PrivateLessonDTO privateLessonDTO) {
        return new ResponseEntity<>(this.privateLessonService.editLesson(privateLessonDTO), HttpStatus.OK);
    }
}

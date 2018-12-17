package pl.pollubmy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollubmy.server.entity.HelpInStudy;
import pl.pollubmy.server.entity.UserLogged;
import pl.pollubmy.server.entity.dto.HelpInStudyDTO;
import pl.pollubmy.server.enumType.RateOfImportance;
import pl.pollubmy.server.service.HelpInStudyService;

import java.security.Principal;

@RestController
@RequestMapping("/help_lesson")
public class HelpInStudyController {

    private final HelpInStudyService helpInStudyService;

    @Autowired
    public HelpInStudyController(HelpInStudyService helpInStudyService) {
        this.helpInStudyService = helpInStudyService;
    }

    @GetMapping
    private ResponseEntity<?> getHelpInLessonPriority(){
        return new ResponseEntity<>(RateOfImportance.values(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> createNewHelpLesson(final Principal userLogged, @RequestBody final HelpInStudy helpInStudy) {
        String login = UserLogged.getLogin(userLogged);
        return new ResponseEntity<>(this.helpInStudyService.createNewHelpLesson(login, helpInStudy), HttpStatus.CREATED);
    }

    @GetMapping("/my")
    private ResponseEntity<?> getMyHelpLesson(final Principal userLogged) {
        String login = UserLogged.getLogin(userLogged);
        return new ResponseEntity<>(this.helpInStudyService.getMyHelpLesson(login), HttpStatus.OK);
    }

    @GetMapping("/all")
    private ResponseEntity<?> showAllMyHelpLesson() {
        return new ResponseEntity<>(this.helpInStudyService.getAllHelpLesson(), HttpStatus.OK);
    }

    @PatchMapping("/{helpInLessonOfferId}")
    private ResponseEntity<?> deleteHelpLesson(final Principal userLogged, @PathVariable final String helpInLessonOfferId) {
        String login = UserLogged.getLogin(userLogged);
        this.helpInStudyService.deactivateHelpInLesson(login, helpInLessonOfferId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<?> editHelpLesson(
            final Principal userLogged,
            @RequestBody final HelpInStudyDTO editedHelpInStudyDTO) {
        String login = UserLogged.getLogin(userLogged);
        return new ResponseEntity<>(this.helpInStudyService.updateHelpInStudy(login, editedHelpInStudyDTO), HttpStatus.OK);
    }


}

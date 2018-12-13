package pl.pollubmy.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.pollubmy.server.entity.FileInformation;
import pl.pollubmy.server.entity.UserLogged;
import pl.pollubmy.server.service.UploadFileService;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/uploadFile")
public class UploadFileController {

    private final UploadFileService uploadFileService;

    @Autowired
    public UploadFileController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @PostMapping
    public ResponseEntity<?> uploadFile(
            final Principal userLogged,
            @RequestParam("file") MultipartFile file,
            @RequestParam final String storedFileBody) throws IOException {

        String userLogin = UserLogged.getLogin(userLogged);
        FileInformation fileInformation = new ObjectMapper().readValue(storedFileBody, FileInformation.class);

        this.uploadFileService.storeFile(file, userLogin, fileInformation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{fileInformationId}")
    public ResponseEntity<?> deleteUploadFile(final Principal userLogged, @PathVariable final String fileInformationId) {
        String userLogin = UserLogged.getLogin(userLogged);
        this.uploadFileService.deleteFile(userLogin, fileInformationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllInformationAboutFilesStoredInDataBase(final Principal userLogged) {
        String userLogin = UserLogged.getLogin(userLogged);
        return new ResponseEntity<>(this.uploadFileService.getFilesInformation(userLogin), HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getAllInformationAboutUserFiles(final Principal userLogged) {
        String userLogin = UserLogged.getLogin(userLogged);
        return new ResponseEntity<>(this.uploadFileService.getAllMyFiles(userLogin), HttpStatus.OK);
    }
}

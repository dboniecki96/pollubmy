package pl.pollubmy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollubmy.server.entity.ForumPost;
import pl.pollubmy.server.entity.UserLogged;
import pl.pollubmy.server.service.ForumPostService;

import java.security.Principal;

@RestController
@RequestMapping("/post")
public class ForumPostController {

    private final ForumPostService forumPostService;

    @Autowired
    public ForumPostController(ForumPostService forumPostService) {
        this.forumPostService = forumPostService;
    }

    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody final ForumPost newPostBody, final Principal userLogged) {
        String userLogin = UserLogged.getLogin(userLogged);
        this.forumPostService.addNewPost(newPostBody, userLogin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllPost() {
        return new ResponseEntity<>(this.forumPostService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getUserPost(final Principal userLogged) {
        String userLogin = UserLogged.getLogin(userLogged);
        return new ResponseEntity<>(this.forumPostService.getMyPost(userLogin), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> deletePost(final Principal userLogged, @PathVariable final String deactivatePostId) {
        String userLogin = UserLogged.getLogin(userLogged);
        this.forumPostService.deactivatePost(deactivatePostId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

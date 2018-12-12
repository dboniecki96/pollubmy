package pl.pollubmy.server.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollubmy.server.entity.ForumPost;
import pl.pollubmy.server.entity.UserLogged;
import pl.pollubmy.server.entity.dto.ForumPostDTO;
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
    public ResponseEntity<?> getAllPost(final Principal userLogged) {
        String userLogin = UserLogged.getLogin(userLogged);
        return new ResponseEntity<>(this.forumPostService.getAllPost(userLogin), HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getUserPost(final Principal userLogged) {
        String userLogin = UserLogged.getLogin(userLogged);
        return new ResponseEntity<>(this.forumPostService.getMyPost(userLogin), HttpStatus.OK);
    }

    @PatchMapping("/{deactivatePostId}")
    public ResponseEntity<?> deletePost(final Principal userLogged, @PathVariable final String deactivatePostId) {
        String userLogin = UserLogged.getLogin(userLogged);
        this.forumPostService.deactivatePost(userLogin, deactivatePostId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updatePost(final Principal userLogged, @RequestBody final ForumPostDTO updatePost) {
        String userLogin = UserLogged.getLogin(userLogged);
        return new ResponseEntity<>(this.forumPostService.updatePost(userLogin, updatePost), HttpStatus.OK);
    }

    @PatchMapping("/rate/{ratingPostId}")
    public ResponseEntity<?> ratePost(
            final Principal userLogged,
            @PathVariable final String ratingPostId,
            @RequestParam(value = "rate", required = true) final String rate) {
        String userLogin = UserLogged.getLogin(userLogged);
        String sign = this.forumPostService.ratePost(userLogin, ratingPostId, rate);
        return new ResponseEntity<>(sign, HttpStatus.OK);
    }
}

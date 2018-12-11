package pl.pollubmy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollubmy.server.entity.Comment;
import pl.pollubmy.server.entity.UserLogged;
import pl.pollubmy.server.entity.dto.CommentTextToEditDTO;
import pl.pollubmy.server.service.CommentPostService;

import java.security.Principal;

@RestController
@RequestMapping("/comment")
public class CommentPostController {

    private final CommentPostService commentPostService;

    @Autowired
    public CommentPostController(CommentPostService commentPostService) {
        this.commentPostService = commentPostService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<?> addNewComment(
            final Principal userLogged,
            @RequestBody final Comment newComment,
            @PathVariable final String postId) {
        String userLogin = UserLogged.getLogin(userLogged);
        return new ResponseEntity<>(commentPostService.addComment(userLogin, newComment, postId), HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> deleteOwnComment(final Principal userLogged, @PathVariable final String commentId) {
        String userLogin = UserLogged.getLogin(userLogged);
        this.commentPostService.deactivateComment(userLogin, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> editOwnComment(
            final Principal userLogged,
            @RequestBody final CommentTextToEditDTO commentToEdit,
            @PathVariable final String commentId) {
        String userLogin = UserLogged.getLogin(userLogged);
        return new ResponseEntity<>(this.commentPostService.editComment(userLogin, commentToEdit, commentId), HttpStatus.OK);
    }

    @PatchMapping("/rate/{ratingCommentId}")
    public ResponseEntity<?> rateComment(
            final Principal userLogged,
            @PathVariable final String ratingCommentId,
            @RequestParam(value = "rate", required = true) final String rate) {
        String userLogin = UserLogged.getLogin(userLogged);
        this.commentPostService.rateComment(userLogin, ratingCommentId, rate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

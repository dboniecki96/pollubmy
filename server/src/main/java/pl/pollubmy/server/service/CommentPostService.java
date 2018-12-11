package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.Comment;
import pl.pollubmy.server.entity.ForumPost;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.entity.dto.CommentDTO;
import pl.pollubmy.server.entity.dto.CommentDTOConverter;
import pl.pollubmy.server.entity.dto.CommentDTOTextToEdit;
import pl.pollubmy.server.exceptions.CommentNotFoundException;
import pl.pollubmy.server.exceptions.ForumPostNotFoundException;
import pl.pollubmy.server.exceptions.UserNotFoundException;
import pl.pollubmy.server.repository.CommentRepository;
import pl.pollubmy.server.repository.ForumPostRepository;
import pl.pollubmy.server.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentPostService {

    private final CommentRepository commentRepository;
    private final ForumPostRepository forumPostRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentPostService(CommentRepository commentRepository, ForumPostRepository forumPostRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.forumPostRepository = forumPostRepository;
        this.userRepository = userRepository;
    }

    public CommentDTO addComment(String userLogin, Comment newComment, String postId) {
        ForumPost postToComment = checkIfPostExist(postId);
        User commentingUser = checkIfUserExist(userLogin);

        newComment.setPostTime(LocalDateTime.now());
        newComment.setForumPostIdFk(postToComment);
        newComment.setUserIdFk(commentingUser);

        newComment.getForumPostIdFk().getComments().add(newComment);
        newComment.getUserIdFk().getComments().add(newComment);

        this.commentRepository.save(newComment);
        return CommentDTOConverter.toDTO(newComment, commentingUser);
    }


    private User checkIfUserExist(String userLogin) throws UserNotFoundException {
        Optional<User> user = this.userRepository.findByLogin(userLogin);
        if (!user.isPresent()) throw new UserNotFoundException("User not found");
        return user.get();
    }

    private ForumPost checkIfPostExist(String postId) throws ForumPostNotFoundException {
        Optional<ForumPost> post = this.forumPostRepository.findById(postId);
        if (!post.isPresent()) throw new ForumPostNotFoundException("Post not found");
        return post.get();
    }

    public void deactivateComment(String userLogin, String commentId) {
        User deletingCommentUser = checkIfUserExist(userLogin);
        Comment commentToDelete = checkIfCommentExist(commentId);
        checkIfCommentBelongToUser(deletingCommentUser, commentToDelete);
        commentToDelete.setActive(false);
        this.commentRepository.save(commentToDelete);
    }

    private Comment checkIfCommentExist(String commentId) {
        Optional<Comment> comment = this.commentRepository.findById(commentId);
        if (!comment.isPresent()) throw new CommentNotFoundException("Comment not found");
        return comment.get();
    }

    private void checkIfCommentBelongToUser(User deletingCommentUser, Comment commentToDelete) {
        if (!(commentToDelete.getUserIdFk().getUserId().equals(deletingCommentUser.getUserId()))) {
            throw new CommentNotFoundException("This comment's doesn't belong to user");
        }
    }

    public CommentDTO editComment(String userLogin, CommentDTOTextToEdit commentToEdit, String commentId) {
        User editCommentUser = checkIfUserExist(userLogin);
        Comment comment = checkIfCommentExist(commentId);
        checkIfCommentBelongToUser(editCommentUser, comment);
        comment.setText(commentToEdit.getText());
        this.commentRepository.save(comment);
        return CommentDTOConverter.toDTO(comment, editCommentUser);
    }


}

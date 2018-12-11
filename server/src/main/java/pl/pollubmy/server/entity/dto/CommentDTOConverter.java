package pl.pollubmy.server.entity.dto;

import pl.pollubmy.server.entity.Comment;
import pl.pollubmy.server.entity.User;

public class CommentDTOConverter {

    public CommentDTOConverter() {
    }

    public static CommentDTO toDTO(Comment comment, User user){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUserLogin(user.getLogin());
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setPoints(comment.getPoints());
        commentDTO.setPostTime(comment.getPostTime());
        commentDTO.setText(comment.getText());
        return commentDTO;
    }
}

package pl.pollubmy.server.entity.dto;

import pl.pollubmy.server.entity.Comment;

public class CommentDTOConverter {

    public CommentDTOConverter() {
    }

    public static CommentDTO toDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUserLogin(comment.getUserIdFk().getLogin());
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setPoints(comment.getPoints());
        commentDTO.setPostTime(comment.getPostTime());
        commentDTO.setText(comment.getText());
        commentDTO.setRate(comment.getRating());
        return commentDTO;
    }
}

package pl.pollubmy.server.entity.dto;

import pl.pollubmy.server.entity.Comment;
import pl.pollubmy.server.entity.ForumPost;
import pl.pollubmy.server.entity.User;

import java.util.List;

public class ForumPostDTOConverter {

    public ForumPostDTOConverter() {
    }

    public static ForumPostDTO toDTO(ForumPost forumPost, User user) {
        ForumPostDTO forumPostDTO = new ForumPostDTO();
        forumPostDTO.setUserLogin(forumPost.getUserIdFk().getLogin());
        forumPostDTO.setForumPostId(forumPost.getForumPostId());
        forumPostDTO.setCategory(forumPost.getCategory());
        forumPostDTO.setPoints(forumPost.getPoints());
        forumPostDTO.setAddPostTime(forumPost.getAddPostTime());
        forumPostDTO.setPostText(forumPost.getPostText());
        forumPostDTO.setTitle(forumPost.getTitle());

        List<Comment> commentList = forumPost.getComments();

        for(Comment comment : commentList) {

            if(comment.isActive()){
                CommentDTO commentAfterConvertToDTO = CommentDTOConverter.toDTO(comment);
                if(!comment.getUserIdFk().getUserId().equals(user.getUserId())){
                    commentAfterConvertToDTO.setOwner(false);
                }
                forumPostDTO.getCommentsDTO().add(commentAfterConvertToDTO);
            }
        }

        return forumPostDTO;
    }
}

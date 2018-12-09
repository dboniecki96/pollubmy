package pl.pollubmy.server.entity.dto;

import pl.pollubmy.server.entity.ForumPost;
import pl.pollubmy.server.entity.User;

public class ForumPostDTOConverter {

    public ForumPostDTOConverter() {
    }

    public static ForumPostDTO toDTO(User user, ForumPost forumPost) {
        ForumPostDTO forumPostDTO = new ForumPostDTO();
        forumPostDTO.setUserLogin(user.getLogin());
        forumPostDTO.setForumPostId(forumPost.getForumPostId());
        forumPostDTO.setCategory(forumPost.getCategory());
        forumPostDTO.setPoints(forumPost.getPoints());
        forumPostDTO.setComments(forumPost.getComments());
        forumPostDTO.setAddPostTime(forumPost.getAddPostTime());
        forumPostDTO.setPostText(forumPost.getPostText());
        forumPostDTO.setTitle(forumPost.getTitle());
        return forumPostDTO;
    }
}

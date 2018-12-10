package pl.pollubmy.server.entity.dto;

import pl.pollubmy.server.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

public class ForumPostDTO {

    private String forumPostId;

    private List<Comment> comments;

    private String category;

    private String title;

    private LocalDateTime addPostTime;

    private Integer points;

    private String postText;

    private String userLogin;

    public String getForumPostId() {
        return forumPostId;
    }

    public void setForumPostId(String forumPostId) {
        this.forumPostId = forumPostId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getAddPostTime() {
        return addPostTime;
    }

    public void setAddPostTime(LocalDateTime addPostTime) {
        this.addPostTime = addPostTime;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

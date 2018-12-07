package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String commentId;

    @JsonBackReference(value = "forumPost-comment")
    @ManyToOne
    @JoinColumn(name = "forumPostIdFk")
    private ForumPost forumPostIdFk;

    @JsonBackReference(value = "user-comment")
    @ManyToOne
    @JoinColumn(name = "userIdFk")
    private User userIdFk;

    private String text;

    @DateTimeFormat
    private LocalDateTime addPostTime = LocalDateTime.now();

    private Integer points = 0;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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


    public ForumPost getForumPostIdFk() {
        return forumPostIdFk;
    }

    public void setForumPostIdFk(ForumPost forumPostIdFk) {
        this.forumPostIdFk = forumPostIdFk;
    }

    public User getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(User userIdFk) {
        this.userIdFk = userIdFk;
    }
}

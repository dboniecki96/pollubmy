package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "commentIdFk", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommentRating> commentRatings;

    private String text;

    @DateTimeFormat
    private LocalDateTime postTime;

    private Integer points = 0;

    @JsonIgnore
    private String rating = "no";

    @JsonIgnore
    private boolean isActive = true;

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

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<CommentRating> getCommentRatings() {
        return commentRatings;
    }

    public void setCommentRatings(List<CommentRating> commentRatings) {
        this.commentRatings = commentRatings;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

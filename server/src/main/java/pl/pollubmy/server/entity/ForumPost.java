package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ForumPost {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String forumPostId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userIdFk")
    private User userIdFk;

    @JsonManagedReference(value = "forumPost-comment")
    @OneToMany(mappedBy = "forumPostIdFk", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "forumPostIdFk", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ForumPostRating> forumPostRatings = new ArrayList<>();

    private String category;

    @DateTimeFormat
    private LocalDateTime addPostTime = LocalDateTime.now();

    private Integer points = 0;

    private String postText;

    private boolean isActive = true;

    private String title;

    private String rating = "no";

    public String getForumPostId() {
        return forumPostId;
    }

    public void setForumPostId(String forumPostId) {
        this.forumPostId = forumPostId;
    }

    @JsonIgnore
    public User getUserIdFk() {
        return userIdFk;
    }

    @JsonProperty
    public void setUserIdFk(User userIdFk) {
        this.userIdFk = userIdFk;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ForumPostRating> getForumPostRatings() {
        return forumPostRatings;
    }

    public void setForumPostRatings(List<ForumPostRating> forumPostRatings) {
        this.forumPostRatings = forumPostRatings;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

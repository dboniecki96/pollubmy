package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ForumPostRating {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String postRatingId;

    @ManyToOne
    @JoinColumn(name = "userIdFk")
    private User userIdFk;

    @ManyToOne
    @JoinColumn(name = "forumPostIdFk")
    @JsonIgnore
    private ForumPost forumPostIdFk;

    private String sign;

    public ForumPostRating() {
    }

    public ForumPostRating(User userIdFk, ForumPost forumPostIdFk, String sign) {
        this.userIdFk = userIdFk;
        this.forumPostIdFk = forumPostIdFk;
        this.sign = sign;
    }

    public String getPostRatingId() {
        return postRatingId;
    }

    public void setPostRatingId(String postRatingId) {
        this.postRatingId = postRatingId;
    }

    public User getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(User userIdFk) {
        this.userIdFk = userIdFk;
    }

    public ForumPost getForumPostIdFk() {
        return forumPostIdFk;
    }

    public void setForumPostIdFk(ForumPost forumPostIdFk) {
        this.forumPostIdFk = forumPostIdFk;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

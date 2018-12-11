package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class CommentRating {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String commentRatingId;

    @OneToOne
    @JoinColumn(name = "userIdFk")
    private User userIdFk;

    @ManyToOne
    @JoinColumn(name = "commentIdFk")
    @JsonIgnore
    private Comment commentIdFk;

    private String sign;

    public CommentRating() {
    }

    public CommentRating(User userIdFk, Comment commentIdFk, String sign) {
        this.userIdFk = userIdFk;
        this.commentIdFk = commentIdFk;
        this.sign = sign;
    }

    public String getCommentRatingId() {
        return commentRatingId;
    }

    public void setCommentRatingId(String commentRatingId) {
        this.commentRatingId = commentRatingId;
    }

    public User getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(User userIdFk) {
        this.userIdFk = userIdFk;
    }

    public Comment getCommentIdFk() {
        return commentIdFk;
    }

    public void setCommentIdFk(Comment commentIdFk) {
        this.commentIdFk = commentIdFk;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

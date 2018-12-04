package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PrivateLessonOffer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String privateLessonOffersId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userIdFk")
    private User userIdFK;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "privateLessonId")
    private PrivateLesson privateLessonIdFK;

    private boolean isActive = true;

    public PrivateLessonOffer() {
    }

    public PrivateLessonOffer(User userIdFK, PrivateLesson privateLessonIdFK) {
        this.userIdFK = userIdFK;
        this.privateLessonIdFK = privateLessonIdFK;
    }

    public String getPrivateLessonOffersId() {
        return privateLessonOffersId;
    }

    public void setPrivateLessonOffersId(String privateLessonOffersId) {
        this.privateLessonOffersId = privateLessonOffersId;
    }

    public User getUserIdFK() {
        return userIdFK;
    }

    public void setUserIdFK(User userIdFK) {
        this.userIdFK = userIdFK;
    }

    public PrivateLesson getPrivateLessonIdFK() {
        return privateLessonIdFK;
    }

    public void setPrivateLessonIdFK(PrivateLesson privateLessonIdFK) {
        this.privateLessonIdFK = privateLessonIdFK;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

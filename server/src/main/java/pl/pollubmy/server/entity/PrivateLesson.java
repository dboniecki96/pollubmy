package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PrivateLesson {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String privateLessonId;

    @JsonBackReference
    @OneToOne(mappedBy = "privateLessonIdFK")
    private PrivateLessonOffer privateLessonOffer;

    @Column(nullable = true)
    private float price;

    @Column(nullable = true)
    private Integer time;

    private String description;

    private String faculty;

    private String fieldOfStudy;

    private String course;

    public PrivateLesson() {
    }

    public String getPrivateLessonId() {
        return privateLessonId;
    }

    public void setPrivateLessonId(String privateLessonId) {
        this.privateLessonId = privateLessonId;
    }

    public PrivateLessonOffer getPrivateLessonOffer() {
        return privateLessonOffer;
    }

    public void setPrivateLessonOffer(PrivateLessonOffer privateLessonOffer) {
        this.privateLessonOffer = privateLessonOffer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}

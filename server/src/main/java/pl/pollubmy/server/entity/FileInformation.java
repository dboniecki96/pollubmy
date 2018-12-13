package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FileInformation {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String fileInformationId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "databaseFileIdFk")
    @JsonIgnore
    private DatabaseFile databaseFileIdFk;

    @ManyToOne
    @JoinColumn(name = "userIdFk")
    private User userIdFk;

    private String faculty;

    private String fieldOfStudy;

    private String course;

    private String description;

    @DateTimeFormat
    private LocalDateTime uploadFileDate = LocalDateTime.now();

    public FileInformation() {
    }

    public String getFileInformationId() {
        return fileInformationId;
    }

    public void setFileInformationId(String fileInformationId) {
        this.fileInformationId = fileInformationId;
    }

    public DatabaseFile getDatabaseFileIdFk() {
        return databaseFileIdFk;
    }

    public void setDatabaseFileIdFk(DatabaseFile databaseFileIdFk) {
        this.databaseFileIdFk = databaseFileIdFk;
    }

    public User getUserIdFk() {
        return userIdFk;
    }

    public void setUserIdFk(User userIdFk) {
        this.userIdFk = userIdFk;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUploadFileDate() {
        return uploadFileDate;
    }

    public void setUploadFileDate(LocalDateTime uploadFileDate) {
        this.uploadFileDate = uploadFileDate;
    }
}

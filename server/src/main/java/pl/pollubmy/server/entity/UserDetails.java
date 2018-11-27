package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UserDetails {

    //Fields

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String userDetailsId;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "userIdFk")
    private User userIdFK;

    //private List<HobbyEnum> hobbies;

    private String secondName;

    private String schoolName;

    private String specialization;

    @Column(nullable = true)
    private int year;

    @Column(nullable = true)
    private int term;

    private String academicDegree;

    private String albumNumber;

    private LocalDate startDate;

    private LocalDate finishDate;

    private LocalDate dateOfBirth;

    // Getters and setters

    public String getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(String userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    public User getUserIdFK() {
        return userIdFK;
    }

    public void setUserIdFK(User userIdFK) {
        this.userIdFK = userIdFK;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getAlbumNumber() {
        return albumNumber;
    }

    public void setAlbumNumber(String albumNumber) {
        this.albumNumber = albumNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinshDate() {
        return finishDate;
    }

    public void setFinshDate(LocalDate finshDate) {
        this.finishDate = finshDate;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

/*    public List<HobbyEnum> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<HobbyEnum> hobbies) {
        this.hobbies = hobbies;
    }*/

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

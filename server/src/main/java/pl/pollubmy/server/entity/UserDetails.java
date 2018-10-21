package pl.pollubmy.server.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UserDetails {

    //Fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userDetailsId;

    @OneToOne
    @JoinColumn(name = "userIdFk")
    private User userIdFK;

    private String secondName;

    private String schoolName;

    private String specialization;

    private int year;

    private int term;

    private String albumNumber;

    private LocalDate startDate;

    private LocalDate finshDate;

    private String PESEL;

    private String academicDegree;


    // Getters and setters


    public Long getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(Long userDetailsId) {
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
        return finshDate;
    }

    public void setFinshDate(LocalDate finshDate) {
        this.finshDate = finshDate;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }
}

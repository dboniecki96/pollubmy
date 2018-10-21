package pl.pollubmy.server.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    //Fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @OneToOne(mappedBy = "userIdFK")
    private UserRole userRole;

    @OneToOne(mappedBy = "userIdFK")
    private UserAddress userAddress;

    @OneToOne(mappedBy = "userIdFK")
    private UserDetails userDetails;

    @OneToMany(mappedBy = "userIdFK")
    private List<Hobby> hobbies = new ArrayList<>();

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private LocalDate dateOfBirth;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    @Size(min = 3)
    private String login;

    @NotNull
    @NotEmpty
    @Email
    @Column(unique = true)
    private String emailPollub;

    @NotNull
    @NotEmpty
    private String password;

    private boolean isActive = true;


    // Getters and setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmailPollub() {
        return emailPollub;
    }

    public void setEmailPollub(String emailPollub) {
        this.emailPollub = emailPollub;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

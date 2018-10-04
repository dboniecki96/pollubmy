package pl.pollubmy.server.entity;

import pl.pollubmy.server.enumType.CountryType;
import pl.pollubmy.server.enumType.HobbyType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Arrays;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Size(min = 1)
    @NotNull
    private String firstName;

    private String secondName;

    private String lastName;

    private LocalDate dateOfBirth;

    private CountryType Country;

    private String city;

    private String street;

    private String houseNumber;

    private String apartmentNumber;

    private String postalCode;

    private String login;

    private String emailPollub;

    private String email;

    private String password;

    private byte[] avatar;

    private HobbyType Hobby;

    private int rate;

    private boolean isActive = true;

    /*On windows new File("img\\JBDFav300.png")
    File file = new File("img/JBDFav300.png");
    byte[] picInBytes = new byte[(int) file.length()];
    FileInputStream fileInputStream = new FileInputStream(file);
    fileInputStream.read(picInBytes);
    fileInputStream.close();
    user.setProfilePic(picInBytes);*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public CountryType getCountry() {
        return Country;
    }

    public void setCountry(CountryType country) {
        Country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public HobbyType getHobby() {
        return Hobby;
    }

    public void setHobby(HobbyType hobby) {
        Hobby = hobby;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", Country=" + Country +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", login='" + login + '\'' +
                ", emailPollub='" + emailPollub + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar=" + Arrays.toString(avatar) +
                ", Hobby=" + Hobby +
                ", rate=" + rate +
                ", isActive=" + isActive +
                '}';
    }


}

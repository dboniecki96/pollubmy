package pl.pollubmy.server.entity.dto;

import pl.pollubmy.server.entity.UserAddress;
import pl.pollubmy.server.entity.UserDetails;

public class UserDTO {

    private String userId;

    private String firstName;

    private String lastName;

    private String login;

    private String emailPollub;

    private UserDetails userDetails;

    private UserAddress userAddress;

    public UserDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }
}

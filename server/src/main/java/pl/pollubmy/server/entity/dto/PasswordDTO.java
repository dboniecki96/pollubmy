package pl.pollubmy.server.entity.dto;

public class PasswordDTO {

    //private String oldPassword;

    private String newPassword;

    public PasswordDTO() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import pl.pollubmy.server.enumType.RoleType;

import javax.persistence.*;

@Entity
public class UserRole {

    //Fields

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String userRoleId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userIdFk")
    private User userIdFK;

    @Enumerated(EnumType.STRING)
    private RoleType roleName;


    //Constructor

    public UserRole() {
    }

    public UserRole(RoleType roleName) {
        this.roleName = roleName;
    }

    public UserRole(User userIdFK, RoleType roleName) {
        this.userIdFK = userIdFK;
        this.roleName = roleName;
    }

    // Getters and setters

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUserIdFK() {
        return userIdFK;
    }

    public void setUserIdFK(User userIdFK) {
        this.userIdFK = userIdFK;
    }

    public RoleType getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleType roleName) {
        this.roleName = roleName;
    }

}

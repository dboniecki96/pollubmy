package pl.pollubmy.server.entity;

import pl.pollubmy.server.enumType.RoleType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class UserRole {

    //Fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId;

    @OneToOne
    @JoinColumn(name = "userIdFk")
    @NotNull
    @NotEmpty
    private User userIdFK;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleName;

    // Getters and setters

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
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

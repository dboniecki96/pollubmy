package pl.pollubmy.server.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @OneToMany(mappedBy = "userRoleIdFK")
    private List<Role> roles;


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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

package pl.pollubmy.server.entity;

import javax.persistence.*;

@Entity
public class Role {

    //Fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "UserRoleIdFk")
    private UserRole userRoleIdFK;

    private String name;


    // Getters and setters

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public UserRole getUserRoleIdFK() {
        return userRoleIdFK;
    }

    public void setUserRoleIdFK(UserRole userRoleIdFK) {
        this.userRoleIdFK = userRoleIdFK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

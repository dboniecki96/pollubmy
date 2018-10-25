package pl.pollubmy.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Hobby {

    //Fields

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String hobbyId;

    private String hobbyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdFk")
    private UserDetails userDetailsIdFK;


    // Getters and setters

    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public UserDetails getUserDetailsIdFK() {
        return userDetailsIdFK;
    }

    public void setUserDetailsIdFK(UserDetails userDetailsIdFK) {
        this.userDetailsIdFK = userDetailsIdFK;
    }
}

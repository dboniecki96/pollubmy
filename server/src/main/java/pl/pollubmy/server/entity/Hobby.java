package pl.pollubmy.server.entity;

import javax.persistence.*;

@Entity
public class Hobby {

    //Fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hobbyId;

    private String hobbyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdFk")
    private UserDetails userDetailsIdFK;


    // Getters and setters

    public long getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(long hobbyId) {
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

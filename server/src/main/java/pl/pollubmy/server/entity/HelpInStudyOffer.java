package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class HelpInStudyOffer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String helpInStudyOfferId;

    @ManyToOne
    @JoinColumn(name = "userIdFk")
    private User userIdFk;

    @OneToOne
    @JoinColumn(name = "helpInStudyOfferIdFk")
    private HelpInStudy helpInStudyIdFk;

    private boolean isActive = true;

    public HelpInStudyOffer(User userIdFk, HelpInStudy helpInStudyIdFk) {
        this.userIdFk = userIdFk;
        this.helpInStudyIdFk = helpInStudyIdFk;
    }

    public HelpInStudyOffer() {
    }
}

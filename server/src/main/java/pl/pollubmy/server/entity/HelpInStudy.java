package pl.pollubmy.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import pl.pollubmy.server.enumType.RateOfImportance;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class HelpInStudy {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String helpInStudyId;

    @JsonIgnore
    @OneToOne(mappedBy = "helpInStudyIdFk")
    private HelpInStudyOffer helpInStudyOffer;

    private String description;

    private String faculty;

    private String fieldOfStudy;

    private String course;

    @DateTimeFormat
    private LocalDateTime timeWhenAdWasAdded = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private RateOfImportance rateOfImportance;
}

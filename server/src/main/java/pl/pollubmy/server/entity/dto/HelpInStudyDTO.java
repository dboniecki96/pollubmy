package pl.pollubmy.server.entity.dto;

import lombok.Getter;
import lombok.Setter;
import pl.pollubmy.server.enumType.RateOfImportance;

import java.time.LocalDateTime;

@Getter
@Setter
public class HelpInStudyDTO {

    private String helpInStudyId;

    private String firstName;

    private String emailPollub;

    private String phone;

    private String description;

    private String faculty;

    private String fieldOfStudy;

    private String course;

    private LocalDateTime timeWhenAdWasAdded;

    private RateOfImportance rateOfImportance;
}

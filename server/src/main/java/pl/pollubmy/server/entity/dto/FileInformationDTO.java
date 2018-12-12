package pl.pollubmy.server.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileInformationDTO {

    private String fileInformationId;

    private String faculty;

    private String fieldOfStudy;

    private String course;

    private String description;

    private String fileName;

    private boolean owner = true;
}

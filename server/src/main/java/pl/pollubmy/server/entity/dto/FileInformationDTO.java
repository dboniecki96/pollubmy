package pl.pollubmy.server.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    private LocalDateTime uploadFileDate;
}

package pl.pollubmy.server.entity.dto;

import lombok.Data;

@Data
public class FileInformationDTO {

    private String fileInformationId;

    private String faculty;

    private String fieldOfStudy;

    private String course;

    private String description;

    private String fileName;

    private String fileType;

}

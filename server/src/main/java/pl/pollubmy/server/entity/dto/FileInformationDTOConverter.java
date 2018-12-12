package pl.pollubmy.server.entity.dto;

import pl.pollubmy.server.entity.DatabaseFile;
import pl.pollubmy.server.entity.FileInformation;

public class FileInformationDTOConverter {

    public FileInformationDTOConverter() {
    }

    public FileInformationDTO toDto(FileInformation fileInformation, DatabaseFile databaseFile) {
        FileInformationDTO fileInformationDTO = new FileInformationDTO();
        fileInformationDTO.setFaculty(fileInformation.getFaculty());
        fileInformationDTO.setCourse(fileInformation.getCourse());
        fileInformationDTO.setFieldOfStudy(fileInformation.getFieldOfStudy());
        fileInformationDTO.setFileInformationId(fileInformation.getFileInformationId());
        fileInformationDTO.setDescription(fileInformation.getDescription());

        fileInformationDTO.setFileName(databaseFile.getFileName());
        fileInformationDTO.setFileType(databaseFile.getFileType());

        return fileInformationDTO;
    }
}

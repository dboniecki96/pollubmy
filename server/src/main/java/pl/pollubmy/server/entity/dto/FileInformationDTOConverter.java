package pl.pollubmy.server.entity.dto;

import pl.pollubmy.server.entity.FileInformation;
import pl.pollubmy.server.entity.User;

public class FileInformationDTOConverter {

    public FileInformationDTOConverter() {
    }

    public static FileInformationDTO toDto(FileInformation fileInformation, User user) {
        FileInformationDTO fileInformationDTO = new FileInformationDTO();
        fileInformationDTO.setFaculty(fileInformation.getFaculty());
        fileInformationDTO.setCourse(fileInformation.getCourse());
        fileInformationDTO.setFieldOfStudy(fileInformation.getFieldOfStudy());
        fileInformationDTO.setFileInformationId(fileInformation.getFileInformationId());
        fileInformationDTO.setDescription(fileInformation.getDescription());

        fileInformationDTO.setFileName(fileInformation.getDatabaseFileIdFk().getFileName());

        if (!(fileInformation.getUserIdFk().getUserId().equals(user.getUserId()))) {
            fileInformationDTO.setOwner(false);
        }

        return fileInformationDTO;
    }
}

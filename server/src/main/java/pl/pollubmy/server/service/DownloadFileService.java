package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.DatabaseFile;
import pl.pollubmy.server.entity.FileInformation;
import pl.pollubmy.server.exceptions.FileStorageException;
import pl.pollubmy.server.repository.FileInformationRepository;

import java.util.Optional;

@Service
public class DownloadFileService {

    private final FileInformationRepository fileInformationRepository;

    @Autowired
    public DownloadFileService(FileInformationRepository fileInformationRepository) {
        this.fileInformationRepository = fileInformationRepository;
    }

    public DatabaseFile getFile(String fileInformationId) {
        DatabaseFile fileToDownload = checkIfFileToDownloadExist(fileInformationId);
        return fileToDownload;
    }

    private DatabaseFile checkIfFileToDownloadExist(String fileInformationId) {
        Optional<FileInformation> fileInformation = this.fileInformationRepository.findById(fileInformationId);
        if (!fileInformation.isPresent()) throw new FileStorageException("File not found in DB");
        DatabaseFile fileDownload = fileInformation.get().getDatabaseFileIdFk();
        if (fileDownload == null) throw new FileStorageException("File not found in DB");
        return fileDownload;
    }
}

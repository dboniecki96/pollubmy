package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollubmy.server.entity.FileInformation;

public interface FileInformationRepository extends JpaRepository<FileInformation, String> {
}

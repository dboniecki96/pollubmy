package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollubmy.server.entity.FileInformation;

public interface StoredFileRepository extends JpaRepository<FileInformation, String> {
}

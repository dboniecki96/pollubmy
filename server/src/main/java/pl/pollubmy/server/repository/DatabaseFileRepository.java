package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollubmy.server.entity.DatabaseFile;

@Repository
public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, String> {
}

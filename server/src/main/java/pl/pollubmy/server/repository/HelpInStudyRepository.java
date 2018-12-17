package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollubmy.server.entity.HelpInStudy;

@Repository
public interface HelpInStudyRepository extends JpaRepository<HelpInStudy, String> {
}

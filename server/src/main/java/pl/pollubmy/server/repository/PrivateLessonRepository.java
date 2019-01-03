package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollubmy.server.entity.PrivateLesson;

@Repository
public interface PrivateLessonRepository extends JpaRepository<PrivateLesson, String> {

}

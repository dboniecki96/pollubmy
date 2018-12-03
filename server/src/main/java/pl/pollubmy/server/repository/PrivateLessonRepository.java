package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollubmy.server.entity.PrivateLesson;

public interface PrivateLessonRepository extends JpaRepository<PrivateLesson, String> {

}

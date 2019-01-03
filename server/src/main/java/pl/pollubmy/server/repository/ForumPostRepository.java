package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollubmy.server.entity.ForumPost;

@Repository
public interface ForumPostRepository extends JpaRepository<ForumPost, String> {
}

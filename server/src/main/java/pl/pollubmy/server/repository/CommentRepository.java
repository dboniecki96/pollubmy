package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollubmy.server.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
}

package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollubmy.server.entity.ForumPost;
import pl.pollubmy.server.entity.ForumPostRating;
import pl.pollubmy.server.entity.User;

import java.util.Optional;

@Repository
public interface ForumPostRatingRepository extends JpaRepository<ForumPostRating, String> {
    Optional<ForumPostRating> findByForumPostIdFkAndUserIdFk(ForumPost forumPostIdFK, User userIdFk);
}

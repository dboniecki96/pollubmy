package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollubmy.server.entity.*;

import java.util.Optional;

public interface CommentRatingRepository extends JpaRepository <CommentRating, String> {
    Optional<CommentRating> findByCommentIdFkAndUserIdFk(Comment commentIdFK, User userIdFk);

}

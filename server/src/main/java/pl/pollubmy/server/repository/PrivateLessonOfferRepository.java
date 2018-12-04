package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollubmy.server.entity.PrivateLessonOffer;

@Repository
public interface PrivateLessonOfferRepository extends JpaRepository<PrivateLessonOffer, String> {

}

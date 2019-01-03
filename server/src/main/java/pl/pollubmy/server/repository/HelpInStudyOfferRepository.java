package pl.pollubmy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pollubmy.server.entity.HelpInStudyOffer;

@Repository
public interface HelpInStudyOfferRepository  extends JpaRepository<HelpInStudyOffer, String> {
}

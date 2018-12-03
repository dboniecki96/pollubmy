package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.PrivateLesson;
import pl.pollubmy.server.entity.PrivateLessonOffer;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.entity.studyInformation.StudyInformation;
import pl.pollubmy.server.entity.tool.ReaderXML;
import pl.pollubmy.server.exceptions.UserNotFoundException;
import pl.pollubmy.server.repository.PrivateLessonRepository;
import pl.pollubmy.server.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PrivateLessonService {

    private final PrivateLessonRepository privateLessonRepository;
    private final UserRepository userRepository;

    @Autowired
    public PrivateLessonService(PrivateLessonRepository privateLessonRepository, UserRepository userRepository) {
        this.privateLessonRepository = privateLessonRepository;
        this.userRepository = userRepository;
    }

    public List<StudyInformation> getStudyInformation() {
        return new ReaderXML().scanFile();
    }

    public PrivateLessonOffer newPrivateLesson(PrivateLesson privateLesson, String login) {
        Optional<User> user = this.userRepository.findByLogin(login);

        if (user.isPresent()) {
            User foundUser = user.get();

            PrivateLessonOffer singleOffer = new PrivateLessonOffer(foundUser, privateLesson);

            privateLesson.setPrivateLessonOffer(singleOffer);

            foundUser.getPrivateLessonOffers().add(singleOffer);

            privateLesson.setPrivateLessonOffer(singleOffer);

            this.privateLessonRepository.save(privateLesson);
            this.userRepository.save(foundUser);
        } else {
            throw new UserNotFoundException("User with this login doesn't exist");
        }

        return null;
    }
}

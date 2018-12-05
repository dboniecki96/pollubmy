package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.PrivateLesson;
import pl.pollubmy.server.entity.PrivateLessonOffer;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.entity.dto.PrivateLessonDTO;
import pl.pollubmy.server.entity.dto.PrivateLessonDTOConverter;
import pl.pollubmy.server.entity.studyInformation.StudyInformation;
import pl.pollubmy.server.entity.tool.CopyPropertiesTool;
import pl.pollubmy.server.entity.tool.ReaderXML;
import pl.pollubmy.server.exceptions.PrivateLessonNotFoundException;
import pl.pollubmy.server.exceptions.UserNotFoundException;
import pl.pollubmy.server.exceptions.WrongRequestException;
import pl.pollubmy.server.repository.PrivateLessonOfferRepository;
import pl.pollubmy.server.repository.PrivateLessonRepository;
import pl.pollubmy.server.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrivateLessonService {

    private final PrivateLessonRepository privateLessonRepository;
    private final UserRepository userRepository;
    private final PrivateLessonOfferRepository privateLessonOfferRepository;

    @Autowired
    public PrivateLessonService(PrivateLessonRepository privateLessonRepository, UserRepository userRepository, PrivateLessonOfferRepository privateLessonOfferRepository) {
        this.privateLessonRepository = privateLessonRepository;
        this.userRepository = userRepository;
        this.privateLessonOfferRepository = privateLessonOfferRepository;
    }

    public List<StudyInformation> getStudyInformation() {
        return new ReaderXML().scanFile();
    }

    public void newLesson(PrivateLesson privateLesson, String login) {
        Optional<User> user = this.userRepository.findByLogin(login);

        if (user.isPresent()) {
            User foundUser = user.get();

            PrivateLessonOffer singleOffer = new PrivateLessonOffer(foundUser, privateLesson);

            privateLesson.setPrivateLessonOffer(singleOffer);
            foundUser.getPrivateLessonOffers().add(singleOffer);

            this.privateLessonRepository.save(privateLesson);
            this.userRepository.save(foundUser);
        } else {
            throw new UserNotFoundException("User with this login doesn't exist");
        }
    }

    public List<PrivateLessonDTO> getAll() {
        List<PrivateLessonOffer> privateLessonList = this.privateLessonOfferRepository.findAll().stream().filter(lesson -> lesson.isActive()).collect(Collectors.toList());

        if (!privateLessonList.isEmpty()) {
            return privateLessonList.stream().map(PrivateLessonDTOConverter::toDTO).collect(Collectors.toList());
        } else {
            throw new PrivateLessonNotFoundException("Empty list private lesson");
        }
    }

    public List<PrivateLessonDTO> getMy(String login) {
        Optional<User> user = this.userRepository.findByLogin(login);

        if (user.isPresent()) {
            User foundUser = user.get();
            List<PrivateLessonOffer> privateLessonList = this.privateLessonOfferRepository.findAll().stream()
                    .filter(PrivateLessonOffer::isActive)
                    .filter(lesson -> lesson.getUserIdFK().getUserId().equals(foundUser.getUserId()))
                    .collect(Collectors.toList());
            if (privateLessonList.isEmpty()) throw new PrivateLessonNotFoundException("Empty private lesson list");
            return privateLessonList.stream().map(PrivateLessonDTOConverter::toDTO).collect(Collectors.toList());
        } else {
            throw new UserNotFoundException("User with this login not found");
        }
    }


    public void deactivateLesson(String login, String lessonId) {
        Optional<User> user = this.userRepository.findByLogin(login);

        if (user.isPresent()) {
            Optional<PrivateLessonOffer> privateLessonOffer = this.privateLessonOfferRepository.findById(lessonId);
            if (privateLessonOffer.isPresent()) {
                privateLessonOffer.get().setActive(false);
                this.privateLessonOfferRepository.save(privateLessonOffer.get());
            } else {
                throw new PrivateLessonNotFoundException("Private lesson not found");
            }
        } else {
            throw new UserNotFoundException("User with this login not found");
        }
    }

    public PrivateLesson editLesson(PrivateLessonDTO newValuesPrivateLesson) {
        String privateLessonOfferId = newValuesPrivateLesson.getPrivateLessonId();

        if (privateLessonOfferId == null || privateLessonOfferId.isEmpty()) throw new WrongRequestException("Id can't be null");

        Optional<PrivateLessonOffer> privateLessonOffer = this.privateLessonOfferRepository.findById(privateLessonOfferId);

        if (privateLessonOffer.isPresent()) {

            String privateLessonToEditId = privateLessonOffer.get().getPrivateLessonIdFK().getPrivateLessonId();

            Optional<PrivateLesson> privateLessonToEdit = this.privateLessonRepository.findById(privateLessonToEditId);

            if (privateLessonToEdit.isPresent()) {
                newValuesPrivateLesson.setPrivateLessonId(null);
                CopyPropertiesTool.copyNonNullProperties(newValuesPrivateLesson, privateLessonToEdit.get());
                return this.privateLessonRepository.save(privateLessonToEdit.get());
            } else {
                throw new PrivateLessonNotFoundException("Private lesson not found");
            }
        } else {
            throw new PrivateLessonNotFoundException("Private lesson offer not found");
        }
    }
}
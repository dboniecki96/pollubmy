package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.HelpInStudy;
import pl.pollubmy.server.entity.HelpInStudyOffer;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.entity.dto.HelpInStudyDTO;
import pl.pollubmy.server.entity.dto.HelpInStudyDTOConverter;
import pl.pollubmy.server.entity.tool.CopyPropertiesTool;
import pl.pollubmy.server.exceptions.HelpInStudyNotFoundException;
import pl.pollubmy.server.exceptions.UserNotFoundException;
import pl.pollubmy.server.repository.HelpInStudyOfferRepository;
import pl.pollubmy.server.repository.HelpInStudyRepository;
import pl.pollubmy.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HelpInStudyService {

    private final UserRepository userRepository;
    private final HelpInStudyOfferRepository helpInStudyOfferRepository;
    private final HelpInStudyRepository helpInStudyRepository;

    @Autowired
    public HelpInStudyService(UserRepository userRepository, HelpInStudyOfferRepository helpInStudyOfferRepository, HelpInStudyRepository helpInStudyRepository) {
        this.userRepository = userRepository;
        this.helpInStudyOfferRepository = helpInStudyOfferRepository;
        this.helpInStudyRepository = helpInStudyRepository;
    }

    public HelpInStudy createNewHelpLesson(String login, HelpInStudy helpInStudy) {
        User user = checkIfUserExist(login);

        HelpInStudyOffer newHelpInStudyOffer = new HelpInStudyOffer(user, helpInStudy);

        setForeignKeysBetweenEntities(helpInStudy, user, newHelpInStudyOffer);

        this.helpInStudyRepository.save(helpInStudy);
        this.userRepository.save(user);

        return helpInStudy;
    }

    private void setForeignKeysBetweenEntities(HelpInStudy helpInStudy, User user, HelpInStudyOffer newHelpInStudyOffer) {

        newHelpInStudyOffer.setUserIdFk(user);
        newHelpInStudyOffer.setHelpInStudyIdFk(helpInStudy);

        user.getHelpInStudyOffers().add(newHelpInStudyOffer);
        helpInStudy.setHelpInStudyOffer(newHelpInStudyOffer);
    }

    private User checkIfUserExist(String userLogin) throws UserNotFoundException {
        Optional<User> user = this.userRepository.findByLogin(userLogin);
        if (!user.isPresent()) throw new UserNotFoundException("User not found");
        return user.get();
    }

    public List<HelpInStudyDTO> getMyHelpLesson(String login) {
        User user = checkIfUserExist(login);

        return checkIfUserHasHelpInStudy(user);
    }

    private List<HelpInStudyDTO> checkIfUserHasHelpInStudy(User user) {
        List<HelpInStudyOffer> list = this.helpInStudyOfferRepository.findAll().stream()
                .filter(temp -> temp.getUserIdFk().getUserId().equals(user.getUserId()))
                .filter(HelpInStudyOffer::isActive).collect(Collectors.toList());

        if (list.isEmpty()) throw new HelpInStudyNotFoundException("Help in study not found");

        List<HelpInStudyDTO> helpInStudyForLoggedUser = new ArrayList<>();

        for (HelpInStudyOffer temp : list) {
            HelpInStudyDTO helpInStudyDTO = HelpInStudyDTOConverter.toDTO(temp, temp.getUserIdFk());
            helpInStudyForLoggedUser.add(helpInStudyDTO);
        }

        return helpInStudyForLoggedUser;
    }

    public List<HelpInStudyDTO> getAllHelpLesson() {
        return checkIfHelpInStudyExist();
    }

    private List<HelpInStudyDTO> checkIfHelpInStudyExist() {
        List<HelpInStudyOffer> allOfferList = this.helpInStudyOfferRepository.findAll().stream().filter(HelpInStudyOffer::isActive).collect(Collectors.toList());
        if (allOfferList.isEmpty()) throw new HelpInStudyNotFoundException("Help in study not found");

        List<HelpInStudyDTO> helpInStudyList = new ArrayList<>();

        for (HelpInStudyOffer temp : allOfferList) {
            HelpInStudyDTO helpInStudyDTO = HelpInStudyDTOConverter.toDTO(temp, temp.getUserIdFk());
            helpInStudyList.add(helpInStudyDTO);
        }

        return helpInStudyList;
    }

    public void deactivateHelpInLesson(String login, String helpInLessonOfferId) {
        User user = checkIfUserExist(login);
        HelpInStudyOffer helpInStudyOffer = checkIfHelpInStudyOfferExist(helpInLessonOfferId);
        checkIfHelpInStudyBelongToUser(user, helpInStudyOffer);
        setNoActiveForHelpInLesson(helpInStudyOffer);

        this.helpInStudyOfferRepository.save(helpInStudyOffer);
    }

    private void setNoActiveForHelpInLesson(HelpInStudyOffer helpInStudyOffer) {
        helpInStudyOffer.setActive(false);
    }

    private void checkIfHelpInStudyBelongToUser(User user, HelpInStudyOffer helpInStudyOffer) {
        boolean isUserAdvert = helpInStudyOffer.getUserIdFk().getUserId().equals(user.getUserId());

        if (!isUserAdvert) throw new HelpInStudyNotFoundException("This help in study doesn't belong to this user");
    }

    private HelpInStudyOffer checkIfHelpInStudyOfferExist(String helpInLessonOfferId) {
        Optional<HelpInStudyOffer> helpInStudyOffer = this.helpInStudyOfferRepository.findById(helpInLessonOfferId);
        if (!helpInStudyOffer.isPresent()) throw new HelpInStudyNotFoundException("Help in study not found");
        if (!helpInStudyOffer.get().isActive()) throw new HelpInStudyNotFoundException("Help in study deleted");
        return helpInStudyOffer.get();
    }

    public HelpInStudyDTO updateHelpInStudy(String login, HelpInStudyDTO editedHelpInStudyDTO) {

        if (editedHelpInStudyDTO.getHelpInStudyId() == null)
            throw new HelpInStudyNotFoundException("The id of help in study is required");

        User user = checkIfUserExist(login);

        String helpInStudyOfferId = editedHelpInStudyDTO.getHelpInStudyId();

        HelpInStudyOffer helpInStudyOffer = checkIfHelpInStudyOfferExist(helpInStudyOfferId);

        editedHelpInStudyDTO.setHelpInStudyId(null);

        HelpInStudy updateHelpInStudy = helpInStudyOffer.getHelpInStudyIdFk();

        CopyPropertiesTool.copyNonNullProperties(editedHelpInStudyDTO, updateHelpInStudy);

        this.helpInStudyOfferRepository.save(helpInStudyOffer);

        return HelpInStudyDTOConverter.toDTO(helpInStudyOffer, user);
    }
}

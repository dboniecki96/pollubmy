package pl.pollubmy.server.entity.dto;

import pl.pollubmy.server.entity.HelpInStudyOffer;
import pl.pollubmy.server.entity.User;

public class HelpInStudyDTOConverter {

    public HelpInStudyDTOConverter() {
    }

    public static HelpInStudyDTO toDTO(HelpInStudyOffer helpInStudyOffer, User user) {

        HelpInStudyDTO helpInStudyDTO = new HelpInStudyDTO();
        helpInStudyDTO.setFaculty(helpInStudyOffer.getHelpInStudyIdFk().getFaculty());
        helpInStudyDTO.setFieldOfStudy(helpInStudyOffer.getHelpInStudyIdFk().getFieldOfStudy());
        helpInStudyDTO.setCourse(helpInStudyOffer.getHelpInStudyIdFk().getCourse());

        helpInStudyDTO.setDescription(helpInStudyOffer.getHelpInStudyIdFk().getDescription());
        helpInStudyDTO.setRateOfImportance(helpInStudyOffer.getHelpInStudyIdFk().getRateOfImportance());
        helpInStudyDTO.setEmailPollub(user.getEmailPollub());
        helpInStudyDTO.setPhone(user.getUserDetails().getPhone());
        helpInStudyDTO.setFirstName(user.getFirstName());
        helpInStudyDTO.setTimeWhenAdWasAdded(helpInStudyOffer.getHelpInStudyIdFk().getTimeWhenAdWasAdded());

        helpInStudyDTO.setHelpInStudyId(helpInStudyOffer.getHelpInStudyOfferId());

        return helpInStudyDTO;
    }
}

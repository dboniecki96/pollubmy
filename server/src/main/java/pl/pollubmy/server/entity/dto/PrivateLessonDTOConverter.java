package pl.pollubmy.server.entity.dto;

import pl.pollubmy.server.entity.PrivateLessonOffer;

public class PrivateLessonDTOConverter {

    public PrivateLessonDTOConverter() {
    }

    public static PrivateLessonDTO toDTO(PrivateLessonOffer privateLessonOffer) {
        PrivateLessonDTO privateLessonDTO = new PrivateLessonDTO();
        privateLessonDTO.setPrivateLessonId(privateLessonOffer.getPrivateLessonOffersId());
        privateLessonDTO.setCourse(privateLessonOffer.getPrivateLessonIdFK().getCourse());
        privateLessonDTO.setDescription(privateLessonOffer.getPrivateLessonIdFK().getDescription());
        privateLessonDTO.setFaculty(privateLessonOffer.getPrivateLessonIdFK().getFaculty());
        privateLessonDTO.setFieldOfStudy(privateLessonOffer.getPrivateLessonIdFK().getFieldOfStudy());
        privateLessonDTO.setPrice(privateLessonOffer.getPrivateLessonIdFK().getPrice());
        privateLessonDTO.setTime(privateLessonOffer.getPrivateLessonIdFK().getTime());
        privateLessonDTO.setEmailPollub(privateLessonOffer.getUserIdFK().getEmailPollub());
        privateLessonDTO.setPhone(privateLessonOffer.getUserIdFK().getUserDetails().getPhone());
        privateLessonDTO.setFirstName(privateLessonOffer.getUserIdFK().getFirstName());
        return privateLessonDTO;
    }
}

package pl.pollubmy.server.entity.dto;

import pl.pollubmy.server.entity.User;

public class UserDTOConverter {

    public UserDTOConverter() {
    }

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmailPollub(user.getEmailPollub());
        userDTO.setLogin(user.getLogin());
        userDTO.setUserAddress(user.getUserAddress());
        userDTO.setUserDetails(user.getUserDetails());
        return userDTO;
    }
}

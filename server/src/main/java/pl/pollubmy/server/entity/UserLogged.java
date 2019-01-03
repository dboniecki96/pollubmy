package pl.pollubmy.server.entity;

import pl.pollubmy.server.exceptions.UserNotLoginException;

import java.security.Principal;

public class UserLogged {

    public static String getLogin(Principal principal) {
        if (principal != null) {
            return principal.getName();
        } else {
            throw new UserNotLoginException("User not logged");
        }
    }
}
